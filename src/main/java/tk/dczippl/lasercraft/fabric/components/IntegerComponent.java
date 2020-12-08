package tk.dczippl.lasercraft.fabric.components;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;

public interface IntegerComponent extends ComponentV3 {
	int getValue();
	void setValue(int value);
}
