package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background background;
	Hero hero;
	Asteroid[] asteroids;
	Texture textureBullet;
	static Bullet[] bullets; // доступ к пулям глобальный


	@Override
    // создание фона, героя
	public void create () {
		batch = new SpriteBatch();
		background = new Background();
		hero = new Hero();
		asteroids = new Asteroid[20];// создание астеоидов
		for (int i = 0; i < asteroids.length;i++){
		    asteroids[i] = new Asteroid();
        }
        textureBullet = new Texture("bullet64x32.png");
		bullets = new Bullet[200]; // не более 200 пуль на экране
        for (int i = 0; i < bullets.length;i++){
            bullets[i] = new Bullet();
        }
	}
	public void update(){
	    background.update();
	    hero.update();
        for (int i = 0; i < asteroids.length;i++){
            asteroids[i].update(); // обновление прорисовки астероидов
        }
        for (int i = 0; i < bullets.length;i++){
            if (bullets[i].active) {
                bullets[i].update();
                for (int j = 0; j < asteroids.length;j++){
                    if(asteroids[j].hitBox.contains(bullets[i].position)){ // если поле поражения астероида содержит пулю, то астероид пересоздаем, пулю деактивируем
                        asteroids[j].recreate();
                        bullets[i].deactive();
                    }
                }
            }
        }
    }

	@Override
	public void render () { // прорисовка элементов игры
	    update();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.render(batch);
		hero.render(batch);
		for (int i = 0; i< asteroids.length; i++){
		    asteroids[i].render(batch);
        }
        for (int i = 0; i< bullets.length; i++){
            if(bullets[i].active){// если пуля активна создаем текстуру
                batch.draw(textureBullet, bullets[i].position.x - 32, bullets[i].position.y - 16); // центровка картинки с помощью отнимания от позиции размер картинки
            }
        }
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();

	}
}
