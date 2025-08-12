package me._l4urens_.seven.commends;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import me._l4urens_.seven.Seven;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class worldEditApiTest implements CommandExecutor {

    private final Seven Plugin;

    public worldEditApiTest(Seven plugin) {
        Plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player p) {

            final File file = new File(Plugin.getDataFolder(), "house.schem");

            if (!file.exists() || file == null) {
                p.sendMessage("§cSchematic file not found at: " + file.getAbsolutePath());
                return true;
            }
            ClipboardFormat format = ClipboardFormats.findByFile(file);
            if (format == null) {
                p.sendMessage(file.toString());
                p.sendMessage(file.getAbsoluteFile().toString());
                p.sendMessage("§cCould not determine schematic format.");
                return true;
            }

            final Location location = p.getLocation();
            final World world = BukkitAdapter.adapt(location.getWorld());

            try (final ClipboardReader reader = format.getReader(new FileInputStream(file));
                 final EditSession editSession = WorldEdit.getInstance().newEditSession(world)) {

                final Clipboard clipboard = reader.read();
                final Operation operation = new ClipboardHolder(clipboard)
                        .createPaste(editSession)
                        .to(BlockVector3.at(location.getBlockX(), location.getBlockY(), location.getBlockZ()))
                        .build();
                Operations.complete(operation);

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (WorldEditException e) {
                throw new RuntimeException(e);
            }


        }
        return true;
    }
}
