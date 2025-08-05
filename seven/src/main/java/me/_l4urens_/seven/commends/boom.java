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

public class boom implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player p) {
            Integer num = Integer.parseInt(p.getScoreboard().getEntries().toArray()[0].toString());
            if(!p.getScoreboardTags().contains("can use")){p.sendMessage(ChatColor.RED+"u cant use this command now");
            return false;}
            if (num % 7 == 0 || Integer.toString(num).contains("7")) {
                p.sendMessage(ChatColor.BOLD + (ChatColor.GREEN + "CORRECT"));
                num += 1;
                p.sendMessage(Integer.toString(num));
                TextComponent option1 = new TextComponent("[next]");
                option1.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                option1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/next"));
                option1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to get next number").create()));

                TextComponent option2 = new TextComponent("[boom]");
                option2.setColor(net.md_5.bungee.api.ChatColor.RED);
                option2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "boom"));
                option2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to boom the number").create()));

                TextComponent message = new TextComponent("Choose: ");
                message.addExtra(option1);
                message.addExtra(" or");
                message.addExtra(option2);

                p.spigot().sendMessage(message);

                ScoreboardManager manager = Bukkit.getScoreboardManager();
                Scoreboard scoreboard = manager.getNewScoreboard();

                Objective objective = scoreboard.registerNewObjective("num", "dummy");
                Score score = objective.getScore(num.toString());
                objective.setDisplayName("num");
                score.setScore(1);
                p.setScoreboard(scoreboard);
            } else {
                p.sendMessage(ChatColor.BOLD + (ChatColor.RED + "GAME OVER"));
                p.removeScoreboardTag("can use");
            }
        }
        return true;
    }
}
