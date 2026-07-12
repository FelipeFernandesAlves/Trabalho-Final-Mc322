package com.unicamp.model;

public class CombatStats {
    private float might;      
    private float area;       
    private float cooldownRed;
    private float projSpeed;

    public CombatStats() {
        this.might = 0.0f;
        this.area = 0.0f;
        this.cooldownRed = 0.0f;
        this.projSpeed = 0.0f;
    }

    public void addMight(float bonus) { this.might += bonus; }
    
    public float getMight() { return might; }
    public float getArea() { return area; }
    public float getCooldownReduction() { return cooldownRed; }
    public float getProjectileSpeed() { return projSpeed; }
}