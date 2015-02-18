package com.silentdarknessmc.deathrun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import com.silentdarknessmc.deathrun.Cuboid;

public class Cuboid implements Iterable<Block>, Cloneable, ConfigurationSerializable {

    protected final String worldName;
    protected final int x1;
    protected final int y1;
    protected final int z1;
    protected final int x2;
    protected final int y2;
    protected final int z2;
    private static int[] $SWITCH_TABLE$com$reznek$deathrun$Cuboid$CuboidDirection;

    public Cuboid(Location l1, Location l2) {
        super();
        if (!l1.getWorld().equals(l2.getWorld())) {
            throw new IllegalArgumentException("Locations must be on the same world");
        } else {
            this.worldName = l1.getWorld().getName();
            this.x1 = Math.min(l1.getBlockX(), l2.getBlockX());
            this.y1 = Math.min(l1.getBlockY(), l2.getBlockY());
            this.z1 = Math.min(l1.getBlockZ(), l2.getBlockZ());
            this.x2 = Math.max(l1.getBlockX(), l2.getBlockX());
            this.y2 = Math.max(l1.getBlockY(), l2.getBlockY());
            this.z2 = Math.max(l1.getBlockZ(), l2.getBlockZ());
        }
    }

    public Cuboid(Location l1) {
        this(l1, l1);
    }

    public Cuboid(Cuboid other) {
        this(other.getWorld().getName(), other.x1, other.y1, other.z1, other.x2, other.y2, other.z2);
    }

    public Cuboid(World world, int x1, int y1, int z1, int x2, int y2, int z2) {
        super();
        this.worldName = world.getName();
        this.x1 = Math.min(x1, x2);
        this.x2 = Math.max(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.y2 = Math.max(y1, y2);
        this.z1 = Math.min(z1, z2);
        this.z2 = Math.max(z1, z2);
    }

    private Cuboid(String worldName, int x1, int y1, int z1, int x2, int y2, int z2) {
        super();
        this.worldName = worldName;
        this.x1 = Math.min(x1, x2);
        this.x2 = Math.max(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.y2 = Math.max(y1, y2);
        this.z1 = Math.min(z1, z2);
        this.z2 = Math.max(z1, z2);
    }

    public Cuboid(Map<String, Object> map) {
        super();
        this.worldName = (String) map.get("worldName");
        this.x1 = ((Integer) map.get("x1")).intValue();
        this.x2 = ((Integer) map.get("x2")).intValue();
        this.y1 = ((Integer) map.get("y1")).intValue();
        this.y2 = ((Integer) map.get("y2")).intValue();
        this.z1 = ((Integer) map.get("z1")).intValue();
        this.z2 = ((Integer) map.get("z2")).intValue();
    }

    public Map<String, Object> serialize() {
        HashMap map = new HashMap();

        map.put("worldName", this.worldName);
        map.put("x1", Integer.valueOf(this.x1));
        map.put("y1", Integer.valueOf(this.y1));
        map.put("z1", Integer.valueOf(this.z1));
        map.put("x2", Integer.valueOf(this.x2));
        map.put("y2", Integer.valueOf(this.y2));
        map.put("z2", Integer.valueOf(this.z2));
        return map;
    }

    public Location getLowerNE() {
        return new Location(this.getWorld(), (double) this.x1, (double) this.y1, (double) this.z1);
    }

    public Location getUpperSW() {
        return new Location(this.getWorld(), (double) this.x2, (double) this.y2, (double) this.z2);
    }

    public List<Block> getBlocks() {
        Iterator blockI = this.iterator();
        ArrayList copy = new ArrayList();

        while (blockI.hasNext()) {
            copy.add((Block) blockI.next());
        }

        return copy;
    }

    public Location getCenter() {
        int x1 = this.getUpperX() + 1;
        int y1 = this.getUpperY() + 1;
        int z1 = this.getUpperZ() + 1;

        return new Location(this.getWorld(), (double) this.getLowerX() + (double) (x1 - this.getLowerX()) / 2.0D, (double) this.getLowerY() + (double) (y1 - this.getLowerY()) / 2.0D, (double) this.getLowerZ() + (double) (z1 - this.getLowerZ()) / 2.0D);
    }

    public World getWorld() {
        World world = Bukkit.getWorld(this.worldName);

        if (world == null) {
            throw new IllegalStateException("World \'" + this.worldName + "\' is not loaded");
        } else {
            return world;
        }
    }

    public int getSizeX() {
        return this.x2 - this.x1 + 1;
    }

    public int getSizeY() {
        return this.y2 - this.y1 + 1;
    }

    public int getSizeZ() {
        return this.z2 - this.z1 + 1;
    }

    public int getLowerX() {
        return this.x1;
    }

    public int getLowerY() {
        return this.y1;
    }

    public int getLowerZ() {
        return this.z1;
    }

    public int getUpperX() {
        return this.x2;
    }

    public int getUpperY() {
        return this.y2;
    }

    public int getUpperZ() {
        return this.z2;
    }

    public Block[] corners() {
        Block[] res = new Block[8];
        World w = this.getWorld();

        res[0] = w.getBlockAt(this.x1, this.y1, this.z1);
        res[1] = w.getBlockAt(this.x1, this.y1, this.z2);
        res[2] = w.getBlockAt(this.x1, this.y2, this.z1);
        res[3] = w.getBlockAt(this.x1, this.y2, this.z2);
        res[4] = w.getBlockAt(this.x2, this.y1, this.z1);
        res[5] = w.getBlockAt(this.x2, this.y1, this.z2);
        res[6] = w.getBlockAt(this.x2, this.y2, this.z1);
        res[7] = w.getBlockAt(this.x2, this.y2, this.z2);
        return res;
    }

    public Cuboid expand(Cuboid.CuboidDirection dir, int amount) {
        switch ($SWITCH_TABLE$com$reznek$deathrun$Cuboid$CuboidDirection()[dir.ordinal()]) {
        case 1:
            return new Cuboid(this.worldName, this.x1 - amount, this.y1, this.z1, this.x2, this.y2, this.z2);

        case 2:
            return new Cuboid(this.worldName, this.x1, this.y1, this.z1 - amount, this.x2, this.y2, this.z2);

        case 3:
            return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x2 + amount, this.y2, this.z2);

        case 4:
            return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2, this.z2 + amount);

        case 5:
            return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2 + amount, this.z2);

        case 6:
            return new Cuboid(this.worldName, this.x1, this.y1 - amount, this.z1, this.x2, this.y2, this.z2);

        default:
            throw new IllegalArgumentException("Invalid direction " + dir);
        }
    }

    public Cuboid shift(Cuboid.CuboidDirection dir, int amount) {
        return this.expand(dir, amount).expand(dir.opposite(), -amount);
    }

    public Cuboid outset(Cuboid.CuboidDirection dir, int amount) {
        Cuboid c;

        switch ($SWITCH_TABLE$com$reznek$deathrun$Cuboid$CuboidDirection()[dir.ordinal()]) {
        case 7:
            c = this.expand(Cuboid.CuboidDirection.North, amount).expand(Cuboid.CuboidDirection.South, amount).expand(Cuboid.CuboidDirection.East, amount).expand(Cuboid.CuboidDirection.West, amount);
            break;

        case 8:
            c = this.expand(Cuboid.CuboidDirection.Down, amount).expand(Cuboid.CuboidDirection.Up, amount);
            break;

        case 9:
            c = this.outset(Cuboid.CuboidDirection.Horizontal, amount).outset(Cuboid.CuboidDirection.Vertical, amount);
            break;

        default:
            throw new IllegalArgumentException("Invalid direction " + dir);
        }

        return c;
    }

    public Cuboid inset(Cuboid.CuboidDirection dir, int amount) {
        return this.outset(dir, -amount);
    }

    public boolean contains(int x, int y, int z) {
        return x >= this.x1 && x <= this.x2 && y >= this.y1 && y <= this.y2 && z >= this.z1 && z <= this.z2;
    }

    public boolean contains(Block b) {
        return this.contains(b.getLocation());
    }

    public boolean contains(Location l) {
        return !this.worldName.equals(l.getWorld().getName()) ? false : this.contains(l.getBlockX(), l.getBlockY(), l.getBlockZ());
    }

    public int getVolume() {
        return this.getSizeX() * this.getSizeY() * this.getSizeZ();
    }

    public byte getAverageLightLevel() {
        long total = 0L;
        int n = 0;
        Iterator iterator = this.iterator();

        while (iterator.hasNext()) {
            Block b = (Block) iterator.next();

            if (b.isEmpty()) {
                total += (long) b.getLightLevel();
                ++n;
            }
        }

        return n > 0 ? (byte) ((int) (total / (long) n)) : 0;
    }

    public Cuboid contract() {
        return this.contract(Cuboid.CuboidDirection.Down).contract(Cuboid.CuboidDirection.South).contract(Cuboid.CuboidDirection.East).contract(Cuboid.CuboidDirection.Up).contract(Cuboid.CuboidDirection.North).contract(Cuboid.CuboidDirection.West);
    }

    public Cuboid contract(Cuboid.CuboidDirection dir) {
        Cuboid face = this.getFace(dir.opposite());

        switch ($SWITCH_TABLE$com$reznek$deathrun$Cuboid$CuboidDirection()[dir.ordinal()]) {
        case 1:
            while (face.containsOnly(0) && face.getLowerX() > this.getLowerX()) {
                face = face.shift(Cuboid.CuboidDirection.North, 1);
            }

            return new Cuboid(this.worldName, this.x1, this.y1, this.z1, face.getUpperX(), this.y2, this.z2);

        case 2:
            while (face.containsOnly(0) && face.getLowerZ() > this.getLowerZ()) {
                face = face.shift(Cuboid.CuboidDirection.East, 1);
            }

            return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2, face.getUpperZ());

        case 3:
            while (face.containsOnly(0) && face.getUpperX() < this.getUpperX()) {
                face = face.shift(Cuboid.CuboidDirection.South, 1);
            }

            return new Cuboid(this.worldName, face.getLowerX(), this.y1, this.z1, this.x2, this.y2, this.z2);

        case 4:
            while (face.containsOnly(0) && face.getUpperZ() < this.getUpperZ()) {
                face = face.shift(Cuboid.CuboidDirection.West, 1);
            }

            return new Cuboid(this.worldName, this.x1, this.y1, face.getLowerZ(), this.x2, this.y2, this.z2);

        case 5:
            while (face.containsOnly(0) && face.getUpperY() < this.getUpperY()) {
                face = face.shift(Cuboid.CuboidDirection.Up, 1);
            }

            return new Cuboid(this.worldName, this.x1, face.getLowerY(), this.z1, this.x2, this.y2, this.z2);

        case 6:
            while (face.containsOnly(0) && face.getLowerY() > this.getLowerY()) {
                face = face.shift(Cuboid.CuboidDirection.Down, 1);
            }

            return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x2, face.getUpperY(), this.z2);

        default:
            throw new IllegalArgumentException("Invalid direction " + dir);
        }
    }

    public Cuboid getFace(Cuboid.CuboidDirection dir) {
        switch ($SWITCH_TABLE$com$reznek$deathrun$Cuboid$CuboidDirection()[dir.ordinal()]) {
        case 1:
            return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x1, this.y2, this.z2);

        case 2:
            return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2, this.z1);

        case 3:
            return new Cuboid(this.worldName, this.x2, this.y1, this.z1, this.x2, this.y2, this.z2);

        case 4:
            return new Cuboid(this.worldName, this.x1, this.y1, this.z2, this.x2, this.y2, this.z2);

        case 5:
            return new Cuboid(this.worldName, this.x1, this.y2, this.z1, this.x2, this.y2, this.z2);

        case 6:
            return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y1, this.z2);

        default:
            throw new IllegalArgumentException("Invalid direction " + dir);
        }
    }

    public boolean containsOnly(int blockId) {
        Iterator iterator = this.iterator();

        while (iterator.hasNext()) {
            Block b = (Block) iterator.next();

            if (b.getTypeId() != blockId) {
                return false;
            }
        }

        return true;
    }

    public Cuboid getBoundingCuboid(Cuboid other) {
        if (other == null) {
            return this;
        } else {
            int xMin = Math.min(this.getLowerX(), other.getLowerX());
            int yMin = Math.min(this.getLowerY(), other.getLowerY());
            int zMin = Math.min(this.getLowerZ(), other.getLowerZ());
            int xMax = Math.max(this.getUpperX(), other.getUpperX());
            int yMax = Math.max(this.getUpperY(), other.getUpperY());
            int zMax = Math.max(this.getUpperZ(), other.getUpperZ());

            return new Cuboid(this.worldName, xMin, yMin, zMin, xMax, yMax, zMax);
        }
    }

    public Block getRelativeBlock(int x, int y, int z) {
        return this.getWorld().getBlockAt(this.x1 + x, this.y1 + y, this.z1 + z);
    }

    public Block getRelativeBlock(World w, int x, int y, int z) {
        return w.getBlockAt(this.x1 + x, this.y1 + y, this.z1 + z);
    }

    public List<Chunk> getChunks() {
        ArrayList res = new ArrayList();
        World w = this.getWorld();
        int x1 = this.getLowerX() & -16;
        int x2 = this.getUpperX() & -16;
        int z1 = this.getLowerZ() & -16;
        int z2 = this.getUpperZ() & -16;

        for (int x = x1; x <= x2; x += 16) {
            for (int z = z1; z <= z2; z += 16) {
                res.add(w.getChunkAt(x >> 4, z >> 4));
            }
        }

        return res;
    }

    public Iterator<Block> iterator() {
        return new Cuboid.CuboidIterator(this.getWorld(), this.x1, this.y1, this.z1, this.x2, this.y2, this.z2);
    }

    public Cuboid clone1() {
        return new Cuboid(this);
    }

    public String toString() {
        return new String("Cuboid: " + this.worldName + "," + this.x1 + "," + this.y1 + "," + this.z1 + "=>" + this.x2 + "," + this.y2 + "," + this.z2);
    }

    public Object clone() throws CloneNotSupportedException {
        return this.clone1();
    }

    static int[] $SWITCH_TABLE$com$reznek$deathrun$Cuboid$CuboidDirection() {
        int[] aint = Cuboid.$SWITCH_TABLE$com$reznek$deathrun$Cuboid$CuboidDirection;

        if (Cuboid.$SWITCH_TABLE$com$reznek$deathrun$Cuboid$CuboidDirection != null) {
            return aint;
        } else {
            int[] aint1 = new int[Cuboid.CuboidDirection.values().length];

            try {
                aint1[Cuboid.CuboidDirection.Both.ordinal()] = 9;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

            try {
                aint1[Cuboid.CuboidDirection.Down.ordinal()] = 6;
            } catch (NoSuchFieldError nosuchfielderror1) {
                ;
            }

            try {
                aint1[Cuboid.CuboidDirection.East.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror2) {
                ;
            }

            try {
                aint1[Cuboid.CuboidDirection.Horizontal.ordinal()] = 7;
            } catch (NoSuchFieldError nosuchfielderror3) {
                ;
            }

            try {
                aint1[Cuboid.CuboidDirection.North.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror4) {
                ;
            }

            try {
                aint1[Cuboid.CuboidDirection.South.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror5) {
                ;
            }

            try {
                aint1[Cuboid.CuboidDirection.Unknown.ordinal()] = 10;
            } catch (NoSuchFieldError nosuchfielderror6) {
                ;
            }

            try {
                aint1[Cuboid.CuboidDirection.Up.ordinal()] = 5;
            } catch (NoSuchFieldError nosuchfielderror7) {
                ;
            }

            try {
                aint1[Cuboid.CuboidDirection.Vertical.ordinal()] = 8;
            } catch (NoSuchFieldError nosuchfielderror8) {
                ;
            }

            try {
                aint1[Cuboid.CuboidDirection.West.ordinal()] = 4;
            } catch (NoSuchFieldError nosuchfielderror9) {
                ;
            }

            Cuboid.$SWITCH_TABLE$com$reznek$deathrun$Cuboid$CuboidDirection = aint1;
            return aint1;
        }
    }

    public static enum CuboidDirection {

        North, East, South, West, Up, Down, Horizontal, Vertical, Both, Unknown;

        private static int[] $SWITCH_TABLE$com$reznek$deathrun$Cuboid$CuboidDirection;

        private CuboidDirection() {}

        public Cuboid.CuboidDirection opposite() {
            switch ($SWITCH_TABLE$com$reznek$deathrun$Cuboid$CuboidDirection()[this.ordinal()]) {
            case 1:
                return Cuboid.CuboidDirection.South;

            case 2:
                return Cuboid.CuboidDirection.West;

            case 3:
                return Cuboid.CuboidDirection.North;

            case 4:
                return Cuboid.CuboidDirection.East;

            case 5:
                return Cuboid.CuboidDirection.Down;

            case 6:
                return Cuboid.CuboidDirection.Up;

            case 7:
                return Cuboid.CuboidDirection.Vertical;

            case 8:
                return Cuboid.CuboidDirection.Horizontal;

            case 9:
                return Cuboid.CuboidDirection.Both;

            default:
                return Cuboid.CuboidDirection.Unknown;
            }
        }

        static int[] $SWITCH_TABLE$com$reznek$deathrun$Cuboid$CuboidDirection() {
            int[] aint = Cuboid.CuboidDirection.$SWITCH_TABLE$com$reznek$deathrun$Cuboid$CuboidDirection;

            if (Cuboid.CuboidDirection.$SWITCH_TABLE$com$reznek$deathrun$Cuboid$CuboidDirection != null) {
                return aint;
            } else {
                int[] aint1 = new int[values().length];

                try {
                    aint1[Cuboid.CuboidDirection.Both.ordinal()] = 9;
                } catch (NoSuchFieldError nosuchfielderror) {
                    ;
                }

                try {
                    aint1[Cuboid.CuboidDirection.Down.ordinal()] = 6;
                } catch (NoSuchFieldError nosuchfielderror1) {
                    ;
                }

                try {
                    aint1[Cuboid.CuboidDirection.East.ordinal()] = 2;
                } catch (NoSuchFieldError nosuchfielderror2) {
                    ;
                }

                try {
                    aint1[Cuboid.CuboidDirection.Horizontal.ordinal()] = 7;
                } catch (NoSuchFieldError nosuchfielderror3) {
                    ;
                }

                try {
                    aint1[Cuboid.CuboidDirection.North.ordinal()] = 1;
                } catch (NoSuchFieldError nosuchfielderror4) {
                    ;
                }

                try {
                    aint1[Cuboid.CuboidDirection.South.ordinal()] = 3;
                } catch (NoSuchFieldError nosuchfielderror5) {
                    ;
                }

                try {
                    aint1[Cuboid.CuboidDirection.Unknown.ordinal()] = 10;
                } catch (NoSuchFieldError nosuchfielderror6) {
                    ;
                }

                try {
                    aint1[Cuboid.CuboidDirection.Up.ordinal()] = 5;
                } catch (NoSuchFieldError nosuchfielderror7) {
                    ;
                }

                try {
                    aint1[Cuboid.CuboidDirection.Vertical.ordinal()] = 8;
                } catch (NoSuchFieldError nosuchfielderror8) {
                    ;
                }

                try {
                    aint1[Cuboid.CuboidDirection.West.ordinal()] = 4;
                } catch (NoSuchFieldError nosuchfielderror9) {
                    ;
                }

                Cuboid.CuboidDirection.$SWITCH_TABLE$com$reznek$deathrun$Cuboid$CuboidDirection = aint1;
                return aint1;
            }
        }
    }

    public class CuboidIterator implements Iterator<Block> {

        private World w;
        private int baseX;
        private int baseY;
        private int baseZ;
        private int x;
        private int y;
        private int z;
        private int sizeX;
        private int sizeY;
        private int sizeZ;

        public CuboidIterator(World w, int x1, int y1, int z1, int x2, int y2, int z2) {
            super();
            this.w = w;
            this.baseX = x1;
            this.baseY = y1;
            this.baseZ = z1;
            this.sizeX = Math.abs(x2 - x1) + 1;
            this.sizeY = Math.abs(y2 - y1) + 1;
            this.sizeZ = Math.abs(z2 - z1) + 1;
            this.x = this.y = this.z = 0;
        }

        public boolean hasNext() {
            return this.x < this.sizeX && this.y < this.sizeY && this.z < this.sizeZ;
        }

        public Block next1() {
            Block b = this.w.getBlockAt(this.baseX + this.x, this.baseY + this.y, this.baseZ + this.z);

            if (++this.x >= this.sizeX) {
                this.x = 0;
                if (++this.y >= this.sizeY) {
                    this.y = 0;
                    ++this.z;
                }
            }

            return b;
        }

        public void remove() {}

        public Block next() {
            return this.next1();
        }
    }
}