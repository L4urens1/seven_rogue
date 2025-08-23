package me._l4urens_.seven.commends;


import me._l4urens_.seven.manegers.schemticmanger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class pathtest implements CommandExecutor {

    private final schemticmanger manger ;

    public pathtest(schemticmanger manger) {
        this.manger = manger;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        final long start = System.currentTimeMillis();

        if (sender instanceof Player p) {

           int x = p.getLocation().getBlockX();
           int y = p.getLocation().getBlockY();
           int z = p.getLocation().getBlockZ();
           for(int i =0; i>=3; i++ ) {
//               String filename;
//               if(i%2 == 0 ){
//                    filename = "path1.schem";
//               } else {
//                   filename = "path2.schem";
//               }

               manger.pastSchem(p.getLocation(),p,x,y,z,"path1.schem");

               x += 3;

           }
           p.sendMessage("path made");




            p.sendMessage("§aPasted schematic! Took %dms".formatted(System.currentTimeMillis() - start));

        }
        return true;
    }
}
