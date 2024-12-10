package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;
import com.watabou.utils.Random;

@PropAnnotation
public class UnyieldingHeart extends Prop{
    {
        maxLevel =10;
        value=1;
        valueByLevel =1;
        rate=0.1f;
        rateByLevel =0.01f;
    }

    @Override
    public int icon() {
        return PropIndicator.UNYIELDING_HEART;
    }

    @Override
    public String desc() {
        return Messages.get(this,"desc",
                Messages.decimalFormat("#.##%",getFinallyRate()),
                Messages.decimalFormat("#.##",getFinallyValue()));
    }

    @Override
    public float getFinallyRate() {
        float leftHp = Dungeon.hero.HT-Dungeon.hero.HP;
        float otherRate = leftHp/Dungeon.hero.HT/5;
        return super.getFinallyRate()+otherRate;
    }

    @Override
    public void afterDamaged(int dmg, Object src) {
        if (Random.Float(0,1)<getFinallyRate()){
            Dungeon.hero.heal(getFinallyValue());
        }
    }
}