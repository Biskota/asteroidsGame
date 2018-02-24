package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {
    Vector2 position;
    Texture texture;
    float speed;
    Rectangle hitBox; // поле поражения астероида

    public Asteroid(){
        texture = new Texture("asteroid64.png"); // картинка астероида
        position = new Vector2(MathUtils.random(1280,2560), MathUtils.random(0, 720)); // создание астероида на координатах после правой границы экрана и двигаются влево
        speed = MathUtils.random(3f, 6.0f);
        hitBox = new Rectangle(position.x, position.y , 64,64); // позиция и размеры астероида
    }
    public void render(SpriteBatch batch) { // прорисовка астеродов
        batch.draw(texture, position.x, position.y);
    }

    public void update(){
        position.x -=speed;
        if(position.x < -200){ // если залетают за левую сторону экрана опять появляються справа через метод пересоздания
           recreate();
        }
        hitBox.setPosition(position); // двигаеться за астероидом
    }

    public void recreate(){ // метод пересоздания астероидов
        position.x = MathUtils.random(1280,2560); // новые координаты
        position.y = MathUtils.random(0, 720);
        speed = MathUtils.random(3f, 6.0f); // новая скорость

    }
}
