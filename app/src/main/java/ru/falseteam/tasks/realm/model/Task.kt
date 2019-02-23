package ru.falseteam.tasks.realm.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Task(
        @PrimaryKey open var id: Int = 0,
        open var title: String? = null,
        open var text: String? = null,
        open var isComplete: Boolean = false
) : RealmObject()