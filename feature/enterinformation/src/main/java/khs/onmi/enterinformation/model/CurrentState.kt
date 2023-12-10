package khs.onmi.enterinformation.model

sealed class CurrentState {
    object ENTERSCHOOL : CurrentState()

    object ENTERGRADE : CurrentState()

    object ENTERCLASS : CurrentState()

    object ENTERDEPARTMENT : CurrentState()

    object FINISH : CurrentState()
}