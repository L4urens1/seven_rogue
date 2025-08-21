package me._l4urens_.seven.manegers;

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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class schemticmanger {
    private final Seven Plugin;

    public schemticmanger(Seven plugin) {
        Plugin = plugin;
        this.loadhouse();

    }

    private Clipboard clipboard;

    public void loadhouse(){
        final File file = new File(Plugin.getDataFolder(), "house.schem");
        ClipboardFormat format = ClipboardFormats.findByFile(file);

        try (final ClipboardReader reader = format.getReader(new FileInputStream(file))) {

            this.clipboard = reader.read();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void pasthouse(Location location){
        final World world = BukkitAdapter.adapt(location.getWorld());
        try (final EditSession editSession = WorldEdit.getInstance().newEditSession(world)) {
            final Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .to(BlockVector3.at(location.getBlockX(), location.getBlockY(), location.getBlockZ()))
                    .build();
            Operations.complete(operation);

        }  catch (WorldEditException e) {
            throw new RuntimeException(e);
        }
    }
}
