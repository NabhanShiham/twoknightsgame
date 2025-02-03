package twoknightgame.util;

import lombok.Getter;
import twoknightgame.model.TwoKnightModel;
import twoknightgame.model.Position;

import javafx.beans.property.ReadOnlyObjectWrapper;

public class TwoKnightMoveSelector {

    public enum Phase {
        SELECT_FROM,
        SELECT_TO,
        READY_TO_MOVE

    }

    private final TwoKnightModel model;
    private final ReadOnlyObjectWrapper<Phase> phase;
    @Getter
    private boolean invalidSelection;
    private Position from;
    private Position to;

    public TwoKnightMoveSelector(TwoKnightModel model) {
        this.model = model;
        phase = new ReadOnlyObjectWrapper<>(Phase.SELECT_FROM);
        invalidSelection = false;
    }

    public Phase getPhase() {
        return phase.get();
    }

    public void select(Position position) {
        switch (phase.get()) {
            case SELECT_FROM -> selectFrom(position);
            case SELECT_TO -> selectTo(position);
            case READY_TO_MOVE -> selectFrom(position);
        }
    }

    private void selectFrom(Position position) {
        if (model.isLegalToMoveFrom(position)) {
            from = position;
            phase.set(Phase.SELECT_TO);
            invalidSelection = false;
        } else {
            invalidSelection = true;
        }
    }

    private void selectTo(Position position) {
        if (model.isLegalMove(from, position)) {
            to = position;
            phase.set(Phase.READY_TO_MOVE);
            invalidSelection = false;
        } else {
            invalidSelection = true;
        }
    }

    public Position getFrom() {
        if (phase.get() == Phase.SELECT_FROM) {
            throw new IllegalStateException();
        }
        return from;
    }
    public Position getTo() {
        if (phase.get() != Phase.READY_TO_MOVE) {
            throw new IllegalStateException();
        }
        return to;
    }
    public void reset() {
        from = null;
        to = null;
        phase.set(Phase.SELECT_FROM);
        invalidSelection = false;
    }

}