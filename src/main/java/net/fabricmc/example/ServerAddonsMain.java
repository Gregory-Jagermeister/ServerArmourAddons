package net.fabricmc.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.pb4.polymer.api.resourcepack.PolymerRPUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class ServerAddonsMain implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("polymertest");

	@Override
	public void onInitialize() {
		PolymerRPUtils.markAsRequired();
		PolymerRPUtils.addAssetSource("polymertest");
		PolymerRPUtils.requestArmor(new Identifier("polymertest", "night_vision"));
		ItemRegister.register();
	}
}
