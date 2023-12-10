package khs.onmi.enterinformation.model

sealed class CurrentStage {
    object ENTERSCHOOL : CurrentStage()

    object ENTERGRADE : CurrentStage()

    object ENTERCLASS : CurrentStage()

    object ENTERDEPARTMENT : CurrentStage()

    object FINISH : CurrentStage()
}