package com.unicamp.view.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.unicamp.model.entity.Player;
import com.unicamp.view.renderer.BackgroundRenderer;

public class GameScene extends ScreenAdapter {
    
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final BackgroundRenderer backgroundRenderer;

    // Núcleo de Regras de Negócio
    private final Player player;

    public GameScene() {
        this.batch = new SpriteBatch();
        
        // A câmera define o que vemos na tela. Configuramos para o tamanho atual da janela.
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // 2. Instanciamos o nosso renderizador de cenário
        this.backgroundRenderer = new BackgroundRenderer("ui/grass.png");

        // 3. Inicializamos o modelo
        // Passamos o ID e a posição inicial
        this.player = new Player(1, 0, 0); 
    }

    @Override
    public void render(float delta) {
        // Atualizamos a lógica do jogador.
        player.update(delta);

        // Atualizamos a câmera da libGDX para seguir as coordenadas lógicas do jogador.
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();
        
        // Avisamos ao SpriteBatch para desenhar baseado na visão desta câmera
        batch.setProjectionMatrix(camera.combined);

        // Limpamos a tela do frame anterior (pinta tudo de preto)
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Chamamos o nosso renderizador. 
        backgroundRenderer.render(batch, player, camera.viewportWidth, camera.viewportHeight);
        
    }

    @Override
    public void dispose() {
        // A libGDX exige que limpemos os recursos gráficos da memória manualmente ao fechar a tela
        batch.dispose();
        backgroundRenderer.dispose();
    }

}
