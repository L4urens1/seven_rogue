package me._l4urens_.seven.commends;


import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class startboom implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Player p) {


            Integer num = 1;

            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard scoreboard = manager.getNewScoreboard();

            Objective objective = scoreboard.registerNewObjective("num", "dummy");
            Score score = objective.getScore(num.toString());
            objective.setDisplayName("num");
            score.setScore(1);
            p.setScoreboard(scoreboard);

            TextComponent option1 = new TextComponent("[next]");
            option1.setColor(net.md_5.bungee.api.ChatColor.GREEN);
            option1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND ,"/next"));
            option1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to get next number").create()));

            TextComponent option2 = new TextComponent("[boom]");
            option2.setColor(net.md_5.bungee.api.ChatColor.RED);
            option2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "boom"));
            option2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to boom the number").create()));

            TextComponent message = new TextComponent("Choose: ");
            message.addExtra(option1);
            message.addExtra(" or ");
            message.addExtra(option2);
            p.sendMessage(num.toString());
            p.addScoreboardTag("can use");
            p.sendMessage(p.getScoreboardTags().toArray()[0].toString());
            p.spigot().sendMessage(message);





        } else {
            System.out.println("its a player commend");
        }
        return true;
    }

}
