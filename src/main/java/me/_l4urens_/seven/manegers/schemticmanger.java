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
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class schemticmanger {
    private final Seven Plugin;

    public schemticmanger(Seven plugin) {
        Plugin = plugin;
        this.loadFill("house.schem");
        this.loadFill("pathx1.schem");
        this.loadFill("pathx2.schem");
        this.loadFill("pathz1.schem");
        this.loadFill("pathz2.schem");

    }

    private Clipboard clipboard;
    private Map<String, Clipboard> clipboardl = new HashMap<>();

    public void loadFill(String fillName){
        final File file = new File(Plugin.getDataFolder(), fillName);
        ClipboardFormat format = ClipboardFormats.findByFile(file);

        try (final ClipboardReader reader = format.getReader(new FileInputStream(file))) {

//            this.clipboard =reader.read();


            this.clipboardl.put(fillName,reader.read());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void pastSchem(Location location, Player p, Integer x,Integer y,Integer z,String schemName){


        final World world = BukkitAdapter.adapt(location.getWorld());
        try (final EditSession editSession = WorldEdit.getInstance().newEditSession(world)) {
            final Operation operation = new ClipboardHolder(clipboardl.get(schemName))
                    .createPaste(editSession)
                    .to(BlockVector3.at(x, y, z))
                    .build();
//            p.sendMessage(clipboardl.toString());
            Operations.complete(operation);

        }  catch (WorldEditException e) {
            throw new RuntimeException(e);
        }
    }
}
