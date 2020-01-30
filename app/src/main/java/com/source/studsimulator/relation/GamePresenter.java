package com.source.studsimulator.relation;

import android.graphics.Color;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.source.studsimulator.R;
import com.source.studsimulator.model.ActionObjects;
import com.source.studsimulator.model.GameLogic.PlayerStatsEnum;
import com.source.studsimulator.model.entity.ContainsRandomAction;
import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.entity.Friend;
import com.source.studsimulator.model.entity.Hobby;
import com.source.studsimulator.model.entity.RandomAction;
import com.source.studsimulator.model.entity.Study;
import com.source.studsimulator.model.entity.Work;
import com.source.studsimulator.ui.StudSimulatorApplication;
import com.source.studsimulator.ui.activity.SoundActivity;
import com.source.studsimulator.ui.entity.PlayerStats;
import com.source.studsimulator.ui.entity.ViewState;

import static com.source.studsimulator.model.entity.Work.TypeOfWork.SUMMER;
import static com.source.studsimulator.ui.StudSimulatorApplication.getContext;

public class GamePresenter implements GameContract.Presenter {

    public class PlayerInformation {
        private String course;
        private String studyStage;
        private String programmingSkill;
        private String englishSkill;

        public PlayerInformation(String course, String studyStage,
                                 String programmingSkill, String englishSkill) {
            this.course = course;
            this.studyStage = studyStage;
            this.programmingSkill = programmingSkill;
            this.englishSkill = englishSkill;
        }

        public String getCourse() {
            return course;
        }

        public String getStudyStage() {
            return studyStage;
        }

        public String getProgrammingSkill() {
            return programmingSkill;
        }

        public String getEnglishSkill() {
            return englishSkill;
        }
    }

    private GameContract.Model model;
    private GameContract.View view;
    private ViewState weekLiveChoicesStaff;
    private boolean isFail = false;

    public GamePresenter(GameContract.View newView, GameContract.Model newModel) {
        view = newView;
        model = newModel;
        weekLiveChoicesStaff = new ViewState();
    }

    @Override
    public void viewCreated() {
        updatePlayerStats();
        view.updateWeek(model.getWeek());
        view.updateEnergyLevel(model.getEnergyLevel());
    }

    private boolean CheckMoney() {
        int sum = 0;
        for (Food foodItem : weekLiveChoicesStaff.getFoodList()) {
            sum += foodItem.getPrice().getPrice();
        }
        for (Study studyItem : weekLiveChoicesStaff.getStudyList()) {
            sum += studyItem.getPrice().getPrice();
        }
        for (Work workItem : weekLiveChoicesStaff.getWorkList()) {
            sum -= workItem.getAmountOfMoney();
        }
        for (Hobby hobby : weekLiveChoicesStaff.getHobbyList()) {
            sum += hobby.getPrice().getPrice();
        }

        return sum > model.getParameter(PlayerStatsEnum.MONEY);
    }

    @Override
    public void clickOnNewWeekButton(int energy) {
        if (CheckMoney()) {
            sendNoMoneyToast();
            return;
        }

        view.cleanRandomActionsMessages();
        applyLiveChoices(weekLiveChoicesStaff);
        model.newWeek(energy);
        eraseExcessLiveChoices();
        updatePlayerStats();
        view.updateWeek(model.getWeek());
        view.updateEnergyLevel(model.getEnergyLevel());
        view.updateWeekInformation(new PlayerInformation(
                String.valueOf(model.getWeek() / 52 + 1),
                model.getStudyStage(),
                getProgrammingSkillString(model.getParameter(PlayerStatsEnum.PROGRAMMING_SKILL)),
                getEnglishSkillString(model.getParameter(PlayerStatsEnum.ENGLISH_SKILL))));
        for (Friend friend : ActionObjects.getFriendList()) {
            if (!friend.isMeet()) {
                friend.decreaseCharacteristics();
            }
        }
    }

    private void sendNoMoneyToast() {
        SoundActivity.hearSound(getContext(), R.raw.nomoney);
        Toast clickToast = Toast.makeText(getContext(),
                getContext().getString(R.string.noMoney),
                Toast.LENGTH_SHORT);

        clickToast.setGravity(Gravity.NO_GRAVITY, 0, 0);

        LinearLayout toastContainer = (LinearLayout) clickToast.getView();
        toastContainer.setBackgroundColor(Color.TRANSPARENT);

        ImageView artImage = new ImageView(getContext());
        artImage.setImageResource(R.drawable.nomoney);
        toastContainer.addView(artImage, 0);

        clickToast.show();
    }

    private void applyLiveChoices(ViewState weekLiveChoicesStaff) {
        for (Food foodItem : weekLiveChoicesStaff.getFoodList()) {
            model.eat(foodItem);
            applyRandomAction(foodItem);
        }

        for (Study studyItem : weekLiveChoicesStaff.getStudyList()) {
            model.study(studyItem);
            applyRandomAction(studyItem);
        }

        for (Work workItem : weekLiveChoicesStaff.getWorkList()) {
            model.work(workItem);
            applyRandomAction(workItem);
        }

        for (Hobby hobby : weekLiveChoicesStaff.getHobbyList()) {
            Friend friend = weekLiveChoicesStaff.getFriend();
            if (friend != null) {
                if (!friend.isBusy()) {
                    if (friend.getName().equals(getContext().getString(R.string.carla))) {
                        applyRandomAction(ActionObjects.getAction(14));
                    }
                    if (friend.getName().equals(getContext().getString(R.string.carla)) ||
                            friend.getName().equals(getContext().getString(R.string.spilbergsasha))
                            || friend.getName().equals(getContext().getString(R.string.star))) {
                        applyRandomAction(ActionObjects.getAction(13));
                    }
                    model.hobby(hobby, weekLiveChoicesStaff.getFriend());
                } else {
                    model.hobby(hobby, null);
                    applyRandomAction(friend.getRandomAction());
                }
                friend.changeCharacteristics();
            } else {
                model.hobby(hobby, null);
            }
            applyRandomAction(hobby);
        }

        model.weekCharacteristicDecrease();


        // birthday
        if (model.getWeek() % 52 == 1) {
            applyRandomAction(ActionObjects.getAction(6));
        }
        // money from parents
        applyRandomAction(ActionObjects.getAction(3));

        // stipendia
        if (model.getWeek() % 4 == 1) {
            if (model.getParameter(PlayerStatsEnum.EDUCATION_LEVEL) > 80) {
                applyRandomAction(ActionObjects.getAction(10));
            } else if (model.getParameter(PlayerStatsEnum.EDUCATION_LEVEL) > 50) {
                applyRandomAction(ActionObjects.getAction(15));
            } else {
                applyRandomAction(ActionObjects.getAction(11));
            }
            applyRandomAction(ActionObjects.getAction(12));
        }

        model.normalizeCharacteristics();

        view.updateFragmentSkills(model.getParameter(PlayerStatsEnum.PROGRAMMING_SKILL),
                model.getParameter(PlayerStatsEnum.ENGLISH_SKILL));

        checkIfLoose();
    }

    @Override
    public void clickOnFoodButton(int position) {
        Food food = (Food) ActionObjects.getFoodList().get(position);
        if (food.getEnergyNeeded() > model.getEnergyLevel()) {
            view.notAvailableMessage(StudSimulatorApplication.getContext()
                    .getString(R.string.energyless));
            return;
        }
        weekLiveChoicesStaff.addFood(food);
        model.changeEnergyLevel(-food.getEnergyNeeded());
        changeEnergyLevel();
        view.activateFoodButton(position);
    }

    @Override
    public void unclickOnFoodButton(Food food) {
        weekLiveChoicesStaff.removeFood(food);
        model.changeEnergyLevel(food.getEnergyNeeded());
        changeEnergyLevel();
    }

    @Override
    public void clickOnStudyButton(int position, Study.TYPE_OF_STUDY type) {
        Study study = (Study) ActionObjects.getUniversityList().get(0);
        switch (type) {
            case UNIVERSITY:
                study = (Study) ActionObjects.getUniversityList().get(position);
                break;
            case EXTRA:
                study = (Study) ActionObjects.getExtraActivityList().get(position);
                break;
        }
        if (study.getProgrammingSkillRequired() >
                model.getParameter(PlayerStatsEnum.PROGRAMMING_SKILL)) {
            view.notAvailableMessage(getContext().getString(R.string.programmingless));
            return;
        }
        if (study.getEnglishSkillRequired() > model.getParameter(PlayerStatsEnum.ENGLISH_SKILL)) {
            view.notAvailableMessage(getContext().getString(R.string.englishless));
            return;
        }
        if (study.getEnergyNeeded() > model.getEnergyLevel()) {
            view.notAvailableMessage(getContext().getString(R.string.energyless));
            return;
        }
        weekLiveChoicesStaff.addStudy(study);
        model.changeEnergyLevel(-study.getEnergyNeeded());
        changeEnergyLevel();
        view.activateStudyButton(position, type);
    }

    @Override
    public void unclickOnStudyButton(Study study) {
        weekLiveChoicesStaff.removeStudy(study);
        model.changeEnergyLevel(study.getEnergyNeeded());
        changeEnergyLevel();
    }

    @Override
    public void clickOnWorkButton(int number, Work.TypeOfWork type) {
        Work work = (Work) ActionObjects.getWorkList().get(0);
        switch (type) {
            case SUMMER:
                work = (Work) ActionObjects.getSummerWorkList().get(number);
                break;
            case SIDE:
                work = (Work) ActionObjects.getSideJobList().get(number);
                break;
            case FULL_TIME:
                work = (Work) ActionObjects.getWorkList().get(number);
                break;
        }
        if (type == SUMMER && !model.getStudyStage().equals(StudSimulatorApplication.getContext().getString(R.string.holidays))) {
            view.notAvailableMessage(getContext().getString(R.string.cant_stage));
            return;
        }
        if (work.getProgrammingSkillRequired() > model.getParameter(PlayerStatsEnum.PROGRAMMING_SKILL)) {
            view.notAvailableMessage(getContext().getString(R.string.programmingless));
            return;
        }
        if (work.getEnglishSkillRequired() > model.getParameter(PlayerStatsEnum.ENGLISH_SKILL)) {
            view.notAvailableMessage(getContext().getString(R.string.englishless));
            return;
        }
        if (work.getEnergyNeeded() > model.getEnergyLevel()) {
            view.notAvailableMessage(getContext().getString(R.string.energyless));
            return;
        }
        weekLiveChoicesStaff.addWork(work);
        model.changeEnergyLevel(-work.getEnergyNeeded());
        changeEnergyLevel();
        view.activateWorkButton(number, type);
    }

    @Override
    public void unclickOnWorkButton(Work work) {
        weekLiveChoicesStaff.removeWork(work);
        model.changeEnergyLevel(work.getEnergyNeeded());
        changeEnergyLevel();
    }

    @Override
    public void clickOnHobbyButton(int position, Friend friend) {
        Hobby hobby = (Hobby) ActionObjects.getHobbyList().get(position);
        if (hobby.getEnergyNeeded() > model.getEnergyLevel()) {
            view.notAvailableMessage(getContext().getString(R.string.energyless));
            return;
        }
        weekLiveChoicesStaff.addHobby(hobby);
        weekLiveChoicesStaff.addFriend(friend);
        model.changeEnergyLevel(-hobby.getEnergyNeeded());
        changeEnergyLevel();
        view.activateHobbyButton(position);
    }

    @Override
    public void unclickOnHobbyButton(Hobby hobby, Friend friend) {
        weekLiveChoicesStaff.removeHobby(hobby);
        weekLiveChoicesStaff.removeFriend(friend);
        model.changeEnergyLevel(hobby.getEnergyNeeded());
        changeEnergyLevel();
    }

    @Override
    public void setPlayerSettings(GameSettings settings) {
        model.setPlayerSettings(settings);
    }


    @Override
    public GameSettings getPlayerSettings() {
        return model.getPlayerSettings();
    }

    @Override
    public boolean isLoose() {
        return isFail;
    }

    private void changeEnergyLevel() {
        view.updateEnergyLevel(model.getEnergyLevel());
    }

    private void updatePlayerStats() {
        PlayerStats playerStats = new
                PlayerStats(
                model.getParameter(PlayerStatsEnum.EDUCATION_LEVEL),
                model.getParameter(PlayerStatsEnum.HEALTH),
                model.getParameter(PlayerStatsEnum.SATIETY),
                String.valueOf(model.getParameter(PlayerStatsEnum.MONEY)),
                getProgrammingSkillString(model.getParameter(PlayerStatsEnum.PROGRAMMING_SKILL)),
                getEnglishSkillString(model.getParameter(PlayerStatsEnum.ENGLISH_SKILL)));
        view.refreshPlayerStats(playerStats);
    }

    private String getProgrammingSkillString(int param) {
        if (param < 20) {
            return getContext().getString(R.string.begginer_prog);
        } else if (param < 80) {
            return getContext().getString(R.string.lub_prog);
        } else if (param < 190) {
            return getContext().getString(R.string.jun_prog);
        } else if (param < 340) {
            return getContext().getString(R.string.mid_prog);
        } else if (param < 500) {
            return getContext().getString(R.string.senior_prog);
        } else if (param < 700) {
            return getContext().getString(R.string.lead_prog);
        } else {
            return getContext().getString(R.string.gena_prog);
        }
    }

    private String getEnglishSkillString(int param) {
        if (param < 40) {
            return getContext().getString(R.string.a1);
        } else if (param < 100) {
            return getContext().getString(R.string.a2);
        } else if (param < 180) {
            return getContext().getString(R.string.b1);
        } else if (param < 300) {
            return getContext().getString(R.string.b2);
        } else if (param < 550) {
            return getContext().getString(R.string.c1);
        } else if (param < 800) {
            return getContext().getString(R.string.c2);
        } else {
            return getContext().getString(R.string.d13);
        }
    }

    private void applyRandomAction(ContainsRandomAction item) {
        if (item.getRandomAction() != null) {
            if (item.getRandomAction().isActive()) {
                model.applyRandomAction(item.getRandomAction());
                view.writeRandomActionMessage(item.getRandomAction().getMessage());
            }
        }
    }

    private void applyRandomAction(RandomAction action) {
        if (action != null) {
            if (action.isActive()) {
                model.applyRandomAction(action);
                view.writeRandomActionMessage(action.getMessage());
            }
        }
    }

    private void eraseExcessLiveChoices() {
        if (!model.getStudyStage().equals(StudSimulatorApplication.getContext().getString(R.string.holidays))) {
            for (Work workItem : weekLiveChoicesStaff.getWorkList()) {
                if (ActionObjects.getSummerWorkList().contains(workItem)) {
                    view.unClick(workItem, SUMMER);
                    view.writeRandomActionMessage(getContext().getString(R.string.stage_end));
                }
            }
        }
    }

    private void checkIfLoose() {
        if (model.getWeek() >= 208) {
            if (weekLiveChoicesStaff.getWorkList().isEmpty()) {
                String title = getContext().getString(R.string.win);
                String message = getContext().getString(R.string.winText);
                String button_name = getContext().getString(R.string.repeatText);
                int audio = R.raw.win;
                view.showGameEndMessage(title, message, button_name, audio);
            } else {
                String title = getContext().getString(R.string.loose);
                String message = getContext().getString(R.string.looseText);
                String button_name = getContext().getString(R.string.repeat2Text);
                int audio = R.raw.death;
                view.showGameEndMessage(title, message, button_name, audio);
                isFail = true;
            }
        } else if (model.getParameter(PlayerStatsEnum.SATIETY) == 0 ||
                model.getParameter(PlayerStatsEnum.HEALTH) == 0) {
            String title = getContext().getString(R.string.death);
            String message = getContext().getString(R.string.death_text);
            String button_name = getContext().getString(R.string.death_button);
            int audio = R.raw.death;
            view.showGameEndMessage(title, message, button_name, audio);
            isFail = true;
        } else if (model.getParameter(PlayerStatsEnum.EDUCATION_LEVEL) < 40 &&
                model.getStudyStage().equals(
                        StudSimulatorApplication.getContext().getString(R.string.session))) {
            String title = getContext().getString(R.string.loose2);
            String message = getContext().getString(R.string.loose2Textr);
            String button_name = getContext().getString(R.string.repeat2);
            int audio = R.raw.death;
            view.showGameEndMessage(title, message, button_name, audio);
            isFail = true;
        }
    }

    public static class GameSettings {
        public int gameTime;
        public int money;
        public int health;
        public int satiety;
        public int educationLevel;
        public int programmingSkill;
        public int englishSkill;

        public GameSettings() {
            this.gameTime = 1;
            this.money = 50;
            this.health = 50;
            this.satiety = 50;
            this.educationLevel = 0;
            this.programmingSkill = 0;
            this.englishSkill = 0;
        }
    }
}
