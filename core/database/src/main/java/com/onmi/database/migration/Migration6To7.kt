package com.onmi.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * version code 6 to 7
 * - user_table에 있던 isSkipWeekend, isShowNextDayInfoAfterDinner 컬럼을 신규 option_table로 이관하는 작업.
 * - autoMigration 사용시, 컬럼 삭제가 먼저 이루어지기 때문에 수동 Migration 채택
 */
object Migration6To7 : Migration(6, 7) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // 1) option_table 생성
        db.execSQL(
            """
          CREATE TABLE IF NOT EXISTS `option_table` (
            `primaryKey` TEXT NOT NULL DEFAULT 'primaryKey' PRIMARY KEY,
            `isSkipWeekend` INTEGER NOT NULL DEFAULT 0,
            `isShowNextDayInfoAfterDinner` INTEGER NOT NULL DEFAULT 0
          )
        """.trimIndent()
        )

        // 2) 기존 user_table에서 데이터 복사
        db.execSQL(
            """
          INSERT INTO `option_table` (
            isSkipWeekend, isShowNextDayInfoAfterDinner
          )
          SELECT
            isSkipWeekend, isShowNextDayInfoAfterDinner
          FROM `user_table`
        """.trimIndent()
        )

        // 3) user_table 재구축
        db.execSQL("ALTER TABLE `user_table` RENAME TO `tmp_user_table`")

        // 4) 신규 user_table 생성
        db.execSQL(
            """
          CREATE TABLE IF NOT EXISTS `user_table` (
            `schoolCode` TEXT    NOT NULL PRIMARY KEY,
            `educationCode` TEXT NOT NULL,
            `schoolName` TEXT    NOT NULL,
            `schoolType` TEXT    NOT NULL DEFAULT '',
            `grade` INTEGER      NOT NULL DEFAULT 0,
            `classroom` INTEGER  NOT NULL DEFAULT 0,
            `department` TEXT    NOT NULL DEFAULT ''
          )
        """.trimIndent()
        )

        // 5) 기존 user_table에서 데이터 이관
        db.execSQL(
            """
          INSERT INTO `user_table` (
            schoolCode, educationCode, schoolName,
            schoolType, grade, classroom, department
          )
          SELECT
            schoolCode, educationCode, schoolName,
            schoolType, grade, classroom, department
          FROM `tmp_user_table`
        """.trimIndent()
        )

        // 6) 기존 user_table 삭제
        db.execSQL("DROP TABLE `tmp_user_table`")
    }
}