package com.source.studsimulator.relation;

import com.source.studsimulator.R;
import com.source.studsimulator.model.ActionObjects;
import com.source.studsimulator.model.GameLogic.PlayerStatsEnum;
import com.source.studsimulator.model.entity.ContainsRandomAction;
import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.entity.Hobby;
import com.source.studsimulator.model.entity.RandomAction;
import com.source.studsimulator.model.entity.Study;
import com.source.studsimulator.model.entity.Work;
import com.source.studsimulator.ui.StudSimulatorApplication;
import com.source.studsimulator.ui.entity.PlayerStats;
import com.source.studsimulator.ui.entity.ViewState;

import static com.source.studsimulator.model.entity.Work.TypeOfWork.SUMMER;

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

    @Override
    public void clickOnNewWeekButton(int energy) {
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
            model.hobby(hobbyItem);
            applyRandomAction(hobbyItem);
        }

        model.normalizeCharacteristics();

        if (model.getParameter(PlayerStatsEnum.SATIETY) == 0 ||
                model.getParameter(PlayerStatsEnum.HEALTH) == 0) {
            view.printDeadMessage();
        }

        // birthday
        if (model.getWeek() % 52 == 1) {
            applyRandomAction(ActionObjects.getAction(6));
        }
        // money from parents
        applyRandomAction(ActionObjects.getAction(3));
    }

    void eraseExcessLiveChoices() {
        if (!model.getStudyStage().equals(StudSimulatorApplication.getContext().getString(R.string.holidays))) {
            for (Work workItem : weekLiveChoicesStaff.getWorkList()) {
                if (ActionObjects.getSummerWorkList().contains(workItem)) {
                    view.unClick(workItem, SUMMER);
                    view.writeRandomActionMessage("Стажировка закончилась. Вы заработали кучу долларов и отправляетесь домой");
                }
            }
        }
    }

    @Override
    public void clickOnFoodButton(int position) {
        Food food = (Food) ActionObjects.getFoodList().get(position);
        if (food.getEnergyNeeded() > model.getEnergyLevel()) {
            view.notAvailableMessage("Не достаточно энергии");
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
        ;
        switch (type) {
            case UNIVERSITY:
                study = (Study) ActionObjects.getUniversityList().get(position);
                break;
            case EXTRA:
                study = (Study) ActionObjects.getExtraActivityList().get(position);
                break;
        }
        if (study.getProgrammingSkillRequired() > model.getParameter(PlayerStatsEnum.PROGRAMMING_SKILL)) {
            view.notAvailableMessage("Не достаточный навык программирования");
            return;
        }
        if (study.getEnglishSkillRequired() > model.getParameter(PlayerStatsEnum.ENGLISH_SKILL)) {
            view.notAvailableMessage("Не достаточный навык английского");
            return;
        }
        if (study.getEnergyNeeded() > model.getEnergyLevel()) {
            view.notAvailableMessage("Не достаточно энергии");
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
        ;
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
            view.notAvailableMessage("Стажировки доступны только на каникулах");
            return;
        }
        if (work.getProgrammingSkillRequired() > model.getParameter(PlayerStatsEnum.PROGRAMMING_SKILL)) {
            view.notAvailableMessage("Не достаточный навык программирования");
            return;
        }
        if (work.getEnglishSkillRequired() > model.getParameter(PlayerStatsEnum.ENGLISH_SKILL)) {
            view.notAvailableMessage("Не достаточный навык английского");
            return;
        }
        if (work.getEnergyNeeded() > model.getEnergyLevel()) {
            view.notAvailableMessage("Не достаточно энергии");
            return;
        }
        weekLiveChoicesStaff.addWork(work);
        model.changeEnergyLevel(-work.getEnergyNeeded());
        changeEnergyLevel();
        view.activateWorkButton(number, type);
    }

    @Override
    public void deactivateWorkButton(Work work) {
        weekLiveChoicesStaff.removeWork(work);
        model.changeEnergyLevel(work.getEnergyNeeded());
        changeEnergyLevel();
    }

    @Override
    public void clickOnHobbyButton(int position) {
        Hobby hobby = (Hobby) ActionObjects.getHobbyList().get(position);
        if (hobby.getEnergyNeeded() > model.getEnergyLevel()) {
            view.notAvailableMessage("Не достаточно энергии");
            return;
        }
        weekLiveChoicesStaff.addHobby(hobby);
        model.changeEnergyLevel(-hobby.getEnergyNeeded());
        changeEnergyLevel();
        view.activateHobhyButton(position);
    }

    @Override
    public void unclickOnHobbyButton(Hobby hobby) {
        weekLiveChoicesStaff.removeHobby(hobby);
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
            return StudSimulatorApplication.getContext().getString(R.string.begginer_prog);
        } else if (param < 40) {
            return StudSimulatorApplication.getContext().getString(R.string.lub_prog);
        } else if (param < 80) {
            return StudSimulatorApplication.getContext().getString(R.string.jun_prog);
        } else if (param < 140) {
            return StudSimulatorApplication.getContext().getString(R.string.mid_prog);
        } else if (param < 220) {
            return StudSimulatorApplication.getContext().getString(R.string.senior_prog);
        } else if (param < 300) {
            return StudSimulatorApplication.getContext().getString(R.string.lead_prog);
        } else {
            return StudSimulatorApplication.getContext().getString(R.string.gena_prog);
        }
    }

    private String getEnglishSkillString(int param) {
        if (param < 20) {
            return StudSimulatorApplication.getContext().getString(R.string.a1);
        } else if (param < 40) {
            return StudSimulatorApplication.getContext().getString(R.string.a2);
        } else if (param < 60) {
            return StudSimulatorApplication.getContext().getString(R.string.b1);
        } else if (param < 80) {
            return StudSimulatorApplication.getContext().getString(R.string.b2);
        } else if (param < 100) {
            return StudSimulatorApplication.getContext().getString(R.string.c1);
        } else if (param < 120) {
            return StudSimulatorApplication.getContext().getString(R.string.c2);
        } else {
            return StudSimulatorApplication.getContext().getString(R.string.d13);
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
}
