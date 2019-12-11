package com.source.studsimulator.relation;

import android.graphics.Color;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.source.studsimulator.R;
import com.source.studsimulator.model.ActionObjects;
import com.source.studsimulator.model.GameLogic;
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

import static com.source.studsimulator.ui.StudSimulatorApplication.getContext;

public class GamePresenter implements GameContract.Presenter {

    public class PlayerInformation {
        private String course;
        private String studyStage;
        private String programmingSkill;
        private String englishSkill;

        public PlayerInformation(String course, String studyStage, String programmingSkill, String englishSkill) {
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
        for (Hobby hobbyItem : weekLiveChoicesStaff.getHobbyList()) {
            sum += hobbyItem.getPrice().getPrice();
        }
        return sum > model.getParameter(PlayerStatsEnum.MONEY);
    }

    @Override
    public void clickOnNewWeekButton(int energy) {
        if (CheckMoney()) {
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
            return;
        }

        view.cleanMessages();
        applyLiveChoices(weekLiveChoicesStaff);
        model.newWeek(energy);
        updatePlayerStats();
        view.updateWeek(model.getWeek());
        view.updateEnergyLevel(model.getEnergyLevel());
        view.updateWeekInformation(new PlayerInformation(
                String.valueOf(model.getWeek() / 52 + 1),
                getStudyStage(model.getWeek()),
                getProgrammingSkillString(model.getParameter(PlayerStatsEnum.PROGRAMMING_SKILL)),
                getEnglishSkillString(model.getParameter(PlayerStatsEnum.ENGLISH_SKILL))));
        for (Friend friend : ActionObjects.getFriendList()) {
            if (!friend.isMeet()) {
                friend.decreaseCharacteristics();
            }
        }
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

        for (Hobby hobbyItem : weekLiveChoicesStaff.getHobbyList()) {
            Friend friend = weekLiveChoicesStaff.getFriend();
            if (friend != null) {
                if (!friend.isBusy()) {
                    if (friend.getName().equals("Kарла")) {
                        applyRandomAction(ActionObjects.getAction(14));
                    }
                    if (friend.getName().equals("Карла") || friend.getName().equals("Саша Спилберг") || friend.getName().equals("Стар")) {
                        applyRandomAction(ActionObjects.getAction(13));
                    }
                    model.hobby(hobbyItem, weekLiveChoicesStaff.getFriend());
                } else {
                    model.hobby(hobbyItem, null);
                    applyRandomAction(friend.getRandomAction());
                }
                friend.changeCharacteristics();
            } else {
                model.hobby(hobbyItem, null);
            }
            applyRandomAction(hobbyItem);
        }

        model.weekCharacteristicDecrease();

        applyRandomAction(ActionObjects.getAction(12));

        // birthday
        if (model.getWeek() % 52 == 1) {
            applyRandomAction(ActionObjects.getAction(6));
        }
        // money from parents
        applyRandomAction(ActionObjects.getAction(3));

        if (model.getWeek() % 4 == 1) {
            if (model.getParameter(PlayerStatsEnum.EDUCATION_LEVEL) > 80) {
                applyRandomAction(ActionObjects.getAction(15));
            } else if (model.getParameter(PlayerStatsEnum.EDUCATION_LEVEL) > 50) {
                applyRandomAction(ActionObjects.getAction(10));
            }
        }
        
        model.normalizeCharacteristics();

        if (model.getParameter(PlayerStatsEnum.SATIETY) == 0 ||
                model.getParameter(PlayerStatsEnum.HEALTH) == 0) {
            view.printDeadMessage();
        }

        if (model.getParameter(PlayerStatsEnum.EDUCATION_LEVEL) == 0) {
            applyRandomAction(ActionObjects.getAction(11));
        }
    }

    @Override
    public void clickOnFoodButton(Food food) {
        weekLiveChoicesStaff.addFood(food);
        model.changeEnergyLevel(-food.getEnergyNeeded());
        changeEnergyLevel();
    }

    @Override
    public void unclickOnFoodButton(Food food) {
        weekLiveChoicesStaff.removeFood(food);
        model.changeEnergyLevel(food.getEnergyNeeded());
        changeEnergyLevel();
    }

    @Override
    public void clickOnStudyButton(Study study) {
        weekLiveChoicesStaff.addStudy(study);
        model.changeEnergyLevel(-study.getEnergyNeeded());
        changeEnergyLevel();
    }

    @Override
    public void unclickOnStudyButton(Study study) {
        weekLiveChoicesStaff.removeStudy(study);
        model.changeEnergyLevel(study.getEnergyNeeded());
        changeEnergyLevel();
    }

    @Override
    public void clickOnWorkButton(Work work) {
        weekLiveChoicesStaff.addWork(work);
        model.changeEnergyLevel(-work.getEnergyNeeded());
        changeEnergyLevel();
    }

    @Override
    public void unclickOnWorkButton(Work work) {
        weekLiveChoicesStaff.removeWork(work);
        model.changeEnergyLevel(work.getEnergyNeeded());
        changeEnergyLevel();
    }

    @Override
    public void clickOnHobbyButton(Hobby hobby, Friend friend) {
        weekLiveChoicesStaff.addHobby(hobby);
        weekLiveChoicesStaff.addFriend(friend);
        model.changeEnergyLevel(-hobby.getEnergyNeeded());
        changeEnergyLevel();
    }

    @Override
    public void unclickOnHobbyButton(Hobby hobby, Friend friend) {
        weekLiveChoicesStaff.removeHobby(hobby);
        weekLiveChoicesStaff.removeFriend(friend);
        model.changeEnergyLevel(hobby.getEnergyNeeded());
        changeEnergyLevel();
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
        } else if (param < 60) {
            return getContext().getString(R.string.lub_prog);
        } else if (param < 200) {
            return getContext().getString(R.string.jun_prog);
        } else if (param < 300) {
            return getContext().getString(R.string.mid_prog);
        } else if (param < 400) {
            return getContext().getString(R.string.senior_prog);
        } else if (param < 600) {
            return getContext().getString(R.string.lead_prog);
        } else {
            return getContext().getString(R.string.gena_prog);
        }
    }

    private String getEnglishSkillString(int param) {
        if (param < 20) {
            return getContext().getString(R.string.a1);
        } else if (param < 100) {
            return getContext().getString(R.string.a2);
        } else if (param < 200) {
            return getContext().getString(R.string.b1);
        } else if (param < 300) {
            return getContext().getString(R.string.b2);
        } else if (param < 500) {
            return getContext().getString(R.string.c1);
        } else if (param < 700) {
            return getContext().getString(R.string.c2);
        } else {
            return getContext().getString(R.string.d13);
        }
    }

    private String getStudyStage(int week) {
        week %= 52 + 1;
        if (week <= 16 || (week > 22 && week <= 36)) {
            return getContext().getString(R.string.semestr);
        } else if (week <= 20 || (week > 36 && week <= 40)) {
            return getContext().getString(R.string.session);
        } else {
            return getContext().getString(R.string.holidays);
        }
    }

    private void applyRandomAction(ContainsRandomAction item) {
        if (item.getRandomAction() != null) {
            if (item.getRandomAction().isActive()) {
                model.applyRandomAction(item.getRandomAction());
                view.writeMessage(item.getRandomAction().getMessage());
            }
        }
    }

    private void applyRandomAction(RandomAction action) {
        if (action != null) {
            if (action.isActive()) {
                model.applyRandomAction(action);
                view.writeMessage(action.getMessage());
            }
        }
    }
}
