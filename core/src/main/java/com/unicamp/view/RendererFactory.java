package com.unicamp.view;

import java.util.HashMap;
import java.util.Map;

import com.unicamp.model.entity.Entity;
import com.unicamp.model.entity.Player;
import com.unicamp.model.entity.enemy.CorpoSeco;
import com.unicamp.model.entity.enemy.Diabo;
import com.unicamp.model.entity.enemy.Lobisomem;
import com.unicamp.model.entity.itemdrop.ChickenDrop;
import com.unicamp.model.entity.projectile.BulletProjectile;
import com.unicamp.model.entity.projectile.CrossProjectile;
import com.unicamp.model.entity.itemdrop.CrossDrop;
import com.unicamp.model.entity.itemdrop.PistolDrop;
import com.unicamp.model.entity.itemdrop.WhipDrop;
import com.unicamp.model.entity.projectile.WhipProjectile;
import com.unicamp.view.renderer.BulletRenderer;
import com.unicamp.view.renderer.ChickenDropRenderer;
import com.unicamp.view.renderer.CorpoRenderer;
import com.unicamp.view.renderer.CrossRenderer; 
import com.unicamp.view.renderer.DiaboRenderer;
import com.unicamp.view.renderer.LobisomemRenderer;
import com.unicamp.view.renderer.CrossDropRenderer;
import com.unicamp.view.renderer.PistolDropRenderer;
import com.unicamp.view.renderer.PlayerRenderer;
import com.unicamp.view.renderer.WhipDropRenderer;
import com.unicamp.view.renderer.WhipRenderer;

public class RendererFactory {
    
    private static RendererFactory instance;
    private final Map<Class<? extends Entity>, EntityRenderer<? extends Entity>> renderers;

    private RendererFactory() {
        this.renderers = new HashMap<>();
        
        // Jogador
        addRenderer(Player.class, new PlayerRenderer());
        
        // Inimigos
        addRenderer(CorpoSeco.class, new CorpoRenderer());
        addRenderer(Diabo.class, new DiaboRenderer());
        addRenderer(Lobisomem.class, new LobisomemRenderer());
        
        // Projéteis / Armas
        addRenderer(WhipProjectile.class, new WhipRenderer());
        addRenderer(CrossProjectile.class, new CrossRenderer());
        addRenderer(BulletProjectile.class, new BulletRenderer());

        // Drop de itens
		addRenderer(ChickenDrop.class, new ChickenDropRenderer());
		addRenderer(WhipDrop.class, new WhipDropRenderer());
		addRenderer(CrossDrop.class, new CrossDropRenderer());
		addRenderer(PistolDrop.class, new PistolDropRenderer());
	}

    public static void build() {
        if (instance == null) {
            instance = new RendererFactory();
        }
    }

    public static RendererFactory getInstance() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T extends Entity> EntityRenderer<T> getRenderer(Entity entity) {
        EntityRenderer<?> renderer = renderers.get(entity.getClass());
        
        if (renderer == null) {
            throw new IllegalStateException("Nenhum renderizador registrado para a entidade: " + entity.getClass().getSimpleName());
        }
        
        return (EntityRenderer<T>) renderer;
    }

    public void addRenderer(Class<? extends Entity> entityClass, EntityRenderer<? extends Entity> renderer) {
        renderers.put(entityClass, renderer);
    }
}