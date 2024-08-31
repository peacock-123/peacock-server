package com.peacock.core.extension

import org.jooq.Record
import org.jooq.TableField

@Suppress("UNCHECKED_CAST")
fun <R : Record, T> TableField<R, T?>.asNonNullField(): TableField<R, T> = this as TableField<R, T>
