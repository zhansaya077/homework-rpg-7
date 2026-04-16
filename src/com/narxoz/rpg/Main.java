package com.narxoz.rpg;

import com.narxoz.rpg.combatant.DungeonBoss;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.engine.DungeonEngine;
import com.narxoz.rpg.engine.EncounterResult;
import com.narxoz.rpg.observer.AchievementTracker;
import com.narxoz.rpg.observer.BattleLogger;
import com.narxoz.rpg.observer.HeroStatusMonitor;
import com.narxoz.rpg.observer.LootDropper;
import com.narxoz.rpg.observer.PartySupport;
import com.narxoz.rpg.strategy.AggressiveStrategy;
import com.narxoz.rpg.strategy.BalancedStrategy;
import com.narxoz.rpg.strategy.DefensiveStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Hero warrior = new Hero(
                "Warrior",
                140,
                30,
                10,
                new AggressiveStrategy()
        );

        Hero tank = new Hero(
                "Tank",
                200,
                18,
                20,
                new DefensiveStrategy()
        );

        Hero rogue = new Hero(
                "Rogue",
                110,
                25,
                8,
                new BalancedStrategy()
        );

        List<Hero> heroes = new ArrayList<>();
        heroes.add(warrior);
        heroes.add(tank);
        heroes.add(rogue);

        DungeonBoss boss = new DungeonBoss(
                "Cursed Dragon",
                500,
                35,
                12
        );

        BattleLogger logger = new BattleLogger();
        AchievementTracker tracker = new AchievementTracker();
        PartySupport support = new PartySupport(heroes);
        HeroStatusMonitor monitor = new HeroStatusMonitor();
        LootDropper lootDropper = new LootDropper();

        boss.registerObserver(logger);
        boss.registerObserver(tracker);
        boss.registerObserver(support);
        boss.registerObserver(monitor);
        boss.registerObserver(lootDropper);
        boss.registerObserver(boss);

        DungeonEngine engine = new DungeonEngine();

        EncounterResult result = engine.runEncounter(heroes, boss);

        System.out.println(result);
    }
}
