package cavebiomes.worldgeneration.dungeontypes;

import java.util.Random;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.worldgeneration.DungeonType;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class DungeonPigman extends DungeonType{

	public DungeonPigman() {
		super("NetherZombiePigman");
	}

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.netherrack, 0);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.netherrack, 0);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		int height = 2*MathHelper.abs_int((MathHelper.abs_int(x/2+z) % 10) -5) + (random.nextInt(3)-6);

		if (height < -1 ){
			gen.setFluid(world, x, y, z, Blocks.lava);
		}

		else {
			gen.setBlockWithoutNotify(world, x, y, z, Blocks.netherrack, 0);
			if (random.nextInt(5)==0){
				gen.setBlockWithoutNotify(world, x, y, z, Blocks.nether_wart, 0);
			}

		}
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		if (WTFCaveBiomesConfig.EnableMobSpawners){
			while (world.isAirBlock(x,  y-1,  z)){y--;}
			world.setBlock(x, y, z, Blocks.mob_spawner, 0, 2);
			TileEntityMobSpawner spawner = (TileEntityMobSpawner)world.getTileEntity(x, y, z);
			spawner.func_145881_a().setEntityName("PigZombie");
		}
	}


}