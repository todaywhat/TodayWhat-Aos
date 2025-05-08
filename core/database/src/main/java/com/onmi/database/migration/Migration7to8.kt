package com.onmi.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * version code 7 to 8
 * - option table의 primaryKey필드를 삭제하고, uuid필드를 추가함
 * - SQLite에서는 UUID.randomUUID()함수를 호출할 수 없기 떄문에 SQLite의 randomblob사용
 */
object Migration7to8 : Migration(7, 8) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // 새 테이블 생성
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `option_table_new` (
                `uuid` BLOB NOT NULL PRIMARY KEY,
                `isSkipWeekend` INTEGER NOT NULL DEFAULT 0,
                `isShowNextDayInfoAfterDinner` INTEGER NOT NULL DEFAULT 0
            )
        """.trimIndent()
        )

        // 기존 데이터 복사
        db.execSQL(
            """
            INSERT INTO option_table_new (uuid, isSkipWeekend, isShowNextDayInfoAfterDinner)
            SELECT randomblob(16), isSkipWeekend, isShowNextDayInfoAfterDinner FROM option_table
        """.trimIndent()
        )

        // 기존 테이블 삭제
        db.execSQL("DROP TABLE option_table")

        // 새 테이블 이름 변경
        db.execSQL("ALTER TABLE option_table_new RENAME TO option_table")
    }
}