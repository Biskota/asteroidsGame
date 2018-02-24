package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Bullet {
    Vector2 position;
    float speed;
    boolean active;


        public Bullet(){
            position = new Vector2(0,0); // нахождение в правой верхней точке
            speed = 14f;
            active = false;
        }
        public void activate(float x, float y){ // активация пуль с учетом координат
            position.set(x, y);
            active = true;
        }
         public void deactive(){// деактивация
            active = false;
         }


        public void update(){
            position.x +=speed; // летит вправо
            if(position.x > 1280 ){// при столкновении с правой границей экрана деактивируються
                deactive();
            }
        }
    }
