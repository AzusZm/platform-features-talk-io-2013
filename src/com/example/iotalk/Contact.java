/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.example.iotalk;

import android.database.Cursor;

/**
 * A trivial example of a contact. This is meant to demonstrate how to create an entire object
 * from a single row of a {@link Cursor}.
 */
public class Contact implements CursorCreator<Contact> {
    public final int id;
    public final String displayName;
    public final boolean hasPhoneNumber;

    private Contact() {
        id = -1;
        displayName="nothing";
        hasPhoneNumber=true;
    }

    /** Create a new contact */
    public Contact(int id, String displayName, boolean hasPhoneNumber) {
        this.id = id;
        this.displayName = displayName;
        this.hasPhoneNumber = hasPhoneNumber;
    }

    /** Create a Contact from a single row of a cursor. */
    @Override
    public Contact createFromCursor(Cursor c) {
        // Read all three values in order
        final int id = c.getInt(0);
        final String name = c.getString(1);
        final boolean hasPhoneNumber = c.getInt(2) == 1;
        return new Contact(id, name, hasPhoneNumber);
    }

    public static final Contact FACTORY = new Contact();
}
