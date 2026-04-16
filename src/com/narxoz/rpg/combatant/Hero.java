
package com.narxoz.rpg.combatant;

import com.narxoz.rpg.strategy.CombatStrategy;

public class Hero {

    private final String name;
    private int hp;
    private final int maxHp;
    private final int attackPower;
    private final int defense;
    private CombatStrategy strategy;

    public Hero(String name, int hp, int attackPower, int defense, CombatStrategy strategy) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
        this.strategy = strategy;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefense() {
        return defense;
    }

    public CombatStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(CombatStrategy strategy) {
        this.strategy = strategy;
        System.out.println(name + " switched to " + strategy.getName() + " strategy.");
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int attack() {
        return strategy.calculateDamage(attackPower);
    }

    public int defend() {
        return strategy.calculateDefense(defense);
    }

    public void takeDamage(int amount) {
        int reducedDamage = Math.max(0, amount - defend());
        hp = Math.max(0, hp - reducedDamage);
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }

    public boolean isLowHp() {
        return hp <= maxHp * 0.3;
    }
}
