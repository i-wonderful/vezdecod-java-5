package com.byby.dto.request;

import java.util.List;

public class NewGameRequest {
    private int countQuestions;
    private int minDifficulty;
    private int maxDifficulty;
    private List<Long> categories;

    public int getCountQuestions() {
        return countQuestions;
    }

    public void setCountQuestions(int countQuestions) {
        this.countQuestions = countQuestions;
    }

    public int getMinDifficulty() {
        return minDifficulty;
    }

    public void setMinDifficulty(int minDifficulty) {
        this.minDifficulty = minDifficulty;
    }

    public int getMaxDifficulty() {
        return maxDifficulty;
    }

    public void setMaxDifficulty(int maxDifficulty) {
        this.maxDifficulty = maxDifficulty;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }
}


