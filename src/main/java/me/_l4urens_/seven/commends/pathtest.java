package me._l4urens_.seven.commends;


import me._l4urens_.seven.manegers.schemticmanger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class pathtest implements CommandExecutor {

    private final schemticmanger manger;

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
            for (int i = 0; i <= 2; i++) {
                String filename;

                Float yaw = p.getLocation().getYaw();
                yaw = (yaw % 360 + 360) % 360;
                if ((yaw >= 45 && yaw < 135) || (yaw >= 225 && yaw < 315)) {
                    if (i % 2 == 0) {
                        filename = "pathx1.schem";
                    } else {
                        filename = "pathx2.schem";
                    }
                } else {
                    if (i % 2 == 0) {
                        filename = "pathz1.schem";
                    } else {
                        filename = "pathz2.schem";
                    }
                }
                if (yaw >= 45 && yaw < 135) {
//                   Facing -X

                    manger.pastSchem(p.getLocation(), p, x, y, z, filename);
                } else if (yaw >= 135 && yaw < 225) {
//                    Facing -Z
                    z -= 2;
                    manger.pastSchem(p.getLocation(), p, x, y, z, filename);
                    z += 2;
                } else if (yaw >= 225 && yaw < 315) {
//                    "Facing +X

                    x += 2;
                    manger.pastSchem(p.getLocation(), p, x, y, z, filename);
                    x -= 2;
                } else {
//                    Facing +Z

                    manger.pastSchem(p.getLocation(), p, x, y, z, filename);

                }


                if (yaw >= 45 && yaw < 135) {
//                    p.sendMessage("Facing -X");
                    x -= 3;
                } else if (yaw >= 135 && yaw < 225) {
//                    p.sendMessage("Facing -Z");
                    z -= 3;
                } else if (yaw >= 225 && yaw < 315) {
//                    p.sendMessage("Facing +X");
                    x += 3;
                } else {
//                    p.sendMessage("Facing +Z");
                    z += 3;
                }

            }
            p.sendMessage("path made");


            p.sendMessage("§aPasted schematic! Took %dms".formatted(System.currentTimeMillis() - start));

        }
        return true;
    }
}
