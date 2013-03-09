/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionsAndAnswers;

import java.util.ArrayList;

/**
 *
 * @author neil
 */
public class QuestionSet{
    
    private int id;
    private String name;
    private ArrayList<Question> questions;
    
    public QuestionSet(int id, String name, ArrayList<Question> questions){
        
        this.id = id;
        this.name = name;
        this.questions = questions;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public boolean answerQuestion(int question, int answer){
        return questions.get(question).answerQuestion(answer);
    }
    
    public int getTotalScore(){
        
        int total = 0;
        
        for(Question q : questions){
            total += q.getScore();
        }
        return total;
    }
    
    public ArrayList<Question> getQuestions(){
        return this.questions;
    }
    
    public synchronized void removeQuestion(int questionId) {
 
        Question q = this.getQuestionById(questionId);
        if(q != null){
            this.questions.remove(q);
        }
    }
    
    public synchronized boolean questionIdExistsInQs(int questionId){
        
        Question q = this.getQuestionById(questionId);
        return (q != null) ? true : false;
    }
    
    private synchronized Question getQuestionById(int questionId){
        
        for (Question q : questions) {
            if (questionId == q.getQuestionId()) {
                return q;
            }
        }
        return null;
    }
    
    public synchronized DisplayQuestion[] getQuestionsForDisplay(){
        
        DisplayQuestion[] displayQuestions = new DisplayQuestion[this.questions.size()];
        
        for(int i = 0; i < displayQuestions.length; i++){
            displayQuestions[i] = this.prepareQuestionForDisplay(this.questions.get(i));           
        }
        return displayQuestions;
    }

    private synchronized DisplayQuestion prepareQuestionForDisplay(Question question) {
        
        return new DisplayQuestion(question.getAnswerText(), question.getQuestionText());
    }
}
