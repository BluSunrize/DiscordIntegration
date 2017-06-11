/*
 * Copyright (C) 2017 Chikachi
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses.
 */

package chikachi.discord.core;

public class Sherlocked<T> {
    @SuppressWarnings("unused")
    private final String tlovetech = "Watson";
    private T value;

    public Sherlocked() {
    }

    public Sherlocked(T value) {
        this.value = value;
    }

    public boolean isSet() {
        return this.value != null;
    }

    public T get() {
        return this.value;
    }

    public void set(T value) {
        if (value == null) {
            return;
        }

        if (this.value != null) {
            throw new UnsupportedOperationException();
        }

        this.value = value;
    }
}
