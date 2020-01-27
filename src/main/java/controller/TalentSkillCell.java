package controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TalentSkillCell {
        private final StringProperty name = new SimpleStringProperty();
        private final BooleanProperty added = new SimpleBooleanProperty();

        public TalentSkillCell(String name, boolean added) {
            setName(name);
            setAdded(added);
        }

        public final StringProperty nameProperty() {
            return this.name;
        }

        public final String getName() {
            return this.nameProperty().get();
        }

        public final void setName(final String name) {
            this.nameProperty().set(name);
        }

        public final BooleanProperty addedProperty() {
            return this.added;
        }
        public final boolean isAdded() {
            return this.addedProperty().get();
        }

        public final void setAdded(final boolean added) {
            this.addedProperty().set(added);
        }

        @Override
        public String toString() {
            return getName();
        }

}
