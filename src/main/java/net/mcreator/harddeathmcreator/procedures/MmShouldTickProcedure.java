package net.mcreator.harddeathmcreator.procedures;

public class MmShouldTickProcedure {
	public static boolean execute(double duration) {
		return duration % 20 <= 0;
	}
}
