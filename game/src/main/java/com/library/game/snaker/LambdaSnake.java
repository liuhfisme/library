package com.library.game.snaker;

import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

/**
 * 使用Lambda重写贪吃蛇游戏.
 *
 * @date: 2022-03-28
 * @version: 1.0
 * @author: liufeifei02@beyondsoft.com
 */
public class LambdaSnake extends JFrame {
    PanelL panel;
    Thread thread;
    SnakeL snake;
    FoodL food;

    public LambdaSnake() {
        snake = new SnakeL(10,5,KeyEvent.VK_RIGHT,new Point(5, 5));
        food = new FoodL(snake);
        panel = new PanelL(snake, food);
        thread = new Thread(panel);
        thread.start();
        add(panel);
        addKeyListener(panel);
        setTitle("LambdaSnake");
        setSize(40 * 10 + 15, 40 * 10 + 38);
        setLocation(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LambdaSnake();
    }
}

/**
 * 食物对象。
 */
class FoodL {
    Point point;

    FoodL(SnakeL snake) {
        this.generate(snake);
    }
    /**
     * 食物生成
     *
     * @param snake SnakeL
     */
    FoodL generate(SnakeL snake) {
        Random random = new Random();
        int x = Math.abs(random.nextInt() % 40);
        int y = Math.abs(random.nextInt() % 40);
        if (snake.head.x == x && snake.head.y == y) {
            return this.generate(snake);
        }
        if (snake.body.stream().anyMatch(point -> point.x == x && point.y == y)) {
            return this.generate(snake);
        }
        point = new Point(x, y);
        return this;
    }
}

/**
 * 贪吃蛇。
 */
class SnakeL {
    int size, length;
    int dir;
    List<Point> body;
    Point head;

    SnakeL(int size, int length, int dir, Point head) {
        this.size = size;
        this.length = length;
        this.dir = dir;
        this.head = head;
        this.body = new LinkedList<Point>() {{
            for (int i = 0; i < length -1; i++) {
                add(new Point(head.x, head.y + 1 + i));
            }
        }};
    }

    /**
     * 是否碰触食物
     *
     * @param food
     * @return
     */
    boolean isEat(FoodL food) {
        return head.x == food.point.x && head.y == food.point.y;
    }

    /**
     * 是否越界
     *
     * @return
     */
    boolean isOut() {
        return head.x < 0 || head.y < 0 || head.x >= 40 || head.y >= 40;
    }

    /**
     * 是否吃了自己的身体
     *
     * @return
     */
    boolean isPitch() {
        return body.stream().anyMatch(point -> point.x == head.x && point.y == head.y);
    }

    void dead(int way) {
        System.exit(way);
    }
}

class PanelL extends JPanel implements KeyListener, Runnable {
    SnakeL snake;
    FoodL food;
    int speed;
    Map<Integer, Integer> reverseDir;

    PanelL(SnakeL snake, FoodL food) {
        this.snake = snake;
        this.food = food;
        speed = 8000 / snake.length;
        reverseDir = new LinkedHashMap<Integer, Integer>(){{
           put(KeyEvent.VK_UP, KeyEvent.VK_DOWN);
           put(KeyEvent.VK_DOWN, KeyEvent.VK_UP);
           put(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
           put(KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT);
        }};
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 400, 400);
        g.setColor(Color.YELLOW);
        g.fillRect(snake.head.x * snake.size, snake.head.y * snake.size, snake.size, snake.size);
        g.setColor(Color.ORANGE);
        snake.body.forEach(point -> {
            g.fillRect(point.x * snake.size, point.y * snake.size, snake.size, snake.size);
        });
        g.setColor(Color.GREEN);
        g.fillRect(food.point.x * snake.size, food.point.y * snake.size, snake.size, snake.size);
    }

    public void forward(int dir) {
        Point oxy = new Point(snake.head);
        switch (dir) {
            case KeyEvent.VK_UP:
                snake.head.y--;
                break;
            case KeyEvent.VK_DOWN:
                snake.head.y++;
                break;
            case KeyEvent.VK_LEFT:
                snake.head.x--;
                break;
            case KeyEvent.VK_RIGHT:
                snake.head.x++;
                break;
        }
        if (snake.isOut()) {
            snake.dead(1);
        }
        snake.body.add(0, oxy);
        if (snake.isEat(food)) {
            snake.length++;
            food = food.generate(snake);
        } else {
            snake.body.remove(snake.length -1);
        }
        if (snake.isPitch()){
            snake.dead(2);
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            forward(snake.dir);
        }
        // 同方向加速
        if (keyCode == snake.dir) {
            forward(snake.dir);
        }
        // 反方向失效
        if (keyCode == reverseDir.get(snake.dir)) {
            return;
        }
        if (!(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT
                || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN)) {
            return;
        }
        snake.dir = keyCode;

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Thread.sleep(speed);
            forward(snake.dir);
        }
    }
}