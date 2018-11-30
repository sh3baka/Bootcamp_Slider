package Entity.Effects;

import Entity.Characters.MapObject;

import java.util.ArrayList;

public class Trigger {

    protected boolean isTriggered;
    protected int triggerPositionX;
    protected int stage = 0;
    protected ArrayList<MapObject> objects;


    public Trigger(int triggerPositionX, int stage, ArrayList<MapObject> objects) {
        this.objects = objects;
        this.isTriggered = false;
        this.triggerPositionX = triggerPositionX;
    }

}

