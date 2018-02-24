package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    Vector2 position;
    Texture texture;
    float speed;
    int fireCounter;
    int fireRate;

    public Hero() { // х-ки героя: позиция, картинка, скорость
        position = new Vector2(100, 328);
        texture = new Texture("ship64.png");
        speed = 10.0f;
        fireCounter = 0;// счечик для частоты кадров пули
        fireRate = 8; // частота кадров появления пули

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void update() {// вызывается 60 раз в секунду
        if (Gdx.input.isKeyPressed(Input.Keys.A)) { //при нажатии на клавишу Ф герой двигается назад
            position.x -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {//при нажатии на клавишу В герой двитается вперед
            position.x += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {//при нажатии на клавишу Ы герой двитается вниз
            position.y -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {//при нажатии на клавишу Ц герой двитается вверх
            position.y += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.F)) { // активация пуль клавишей Д
            fireCounter++;
            if(fireCounter >= fireRate){
                fireCounter = 0;
                fire();
            }
        }


        if(Gdx.input.isTouched()){// проверка нажатий на экран тачскрином

            if(Gdx.input.getX() < position.x +32){  // если нажать сзади корабля, то движется назад
                position.x -=speed;
            }
            if(Gdx.input.getX() > position.x +32){ // если нажать спереди корабля, то движется вперед
                position.x +=speed;
            }
            // (720 - место нажатия) потому что подсистема координат которая используется в графике перевернута т.е. координата (0,0)
            // находится в верхнем правом углу,а не в нижнем правом
            if(720 - Gdx.input.getY() < position.y +32){  // если вверху корабля, то движется вверх
                position.y -=speed;
            }
            if(720 - Gdx.input.getY() > position.y + 32){// если внизу  корабля, то движется вниз
                position.y +=speed;
            }

        }
        if (position.x < 0) { //корабль не может улететь за пределы правой границы
            position.x = 0;
        }
        if (position.x > 1216) { //корабль не может улететь за пределы левой границы
            position.x = 1216;
        }
        if(position.y > 720){// если герой залетает за верхнюю границу экрана то появляется снизу
            position.y = -64;
        }
        if(position.y < -64){// если герой залетает за нижнюю границу экрана появляется сверху
            position.y = 720;
        }

    }
    public  void  fire(){
        for (int i = 0; i < MyGdxGame.bullets.length; i++){
            if(!MyGdxGame.bullets[i].active){
                MyGdxGame.bullets[i].activate(position.x + 48, position.y +32); // активация пуль относительно оружия на корабле
                break;
            }
        }
    }

}
