package com.narxoz.rpg;

import com.narxoz.rpg.combatant.DungeonBoss;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.engine.DungeonEngine;
import com.narxoz.rpg.engine.EncounterResult;
import com.narxoz.rpg.observer.*;
import com.narxoz.rpg.strategy.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Hero warrior = new Hero("Warrior", 150, 30, 10, new AggressiveStrategy());
        Hero tank = new Hero("Tank", 220, 18, 25, new DefensiveStrategy());
        Hero rogue = new Hero("Rogue", 120, 25, 8, new BalancedStrategy());

        List<Hero> heroes = new ArrayList<>();
        heroes.add(warrior);
        heroes.add(tank);
        heroes.add(rogue);

        DungeonBoss boss = new DungeonBoss("Cursed Dragon", 600, 35, 12);

        BattleLogger logger = new BattleLogger();
        AchievementTracker tracker = new AchievementTracker();
        PartySupport support = new PartySupport(heroes);
        HeroStatusMonitor monitor = new HeroStatusMonitor();
        LootDropper loot = new LootDropper();

        boss.registerObserver(logger);
        boss.registerObserver(tracker);
        boss.registerObserver(support);
        boss.registerObserver(monitor);
        boss.registerObserver(loot);
        boss.registerObserver(boss);

        DungeonEngine engine = new DungeonEngine();

        EncounterResult result = engine.runEncounter(heroes, boss);

        System.out.println(warrior.getName() + " switches strategy during battle.");
        warrior.setStrategy(new DefensiveStrategy());

        System.out.println(result);
    }
}
