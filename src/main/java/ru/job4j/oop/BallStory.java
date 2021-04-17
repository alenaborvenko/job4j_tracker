package ru.job4j.oop;

public class BallStory {

    public static void main(String[] args) {
        Ball ball = new Ball();
        Hare hare = new Hare();
        hare.tryEat(ball);
        ball.runHare(hare);
        Wolf wolf = new Wolf();
        wolf.tryEat(ball);
        ball.runWolf(wolf);
        Fox fox = new Fox();
        fox.tryEat(ball);
        ball.eaten(fox);
    }
}
