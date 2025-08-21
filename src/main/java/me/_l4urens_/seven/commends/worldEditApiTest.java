package me._l4urens_.seven.commends;


import me._l4urens_.seven.manegers.schemticmanger;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class worldEditApiTest implements CommandExecutor {

    private final schemticmanger manager;

    public worldEditApiTest(schemticmanger manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player p) {


            final Location location = p.getLocation();

            manager.pasthouse(location);


        }
        return true;
    }
}
