import React from 'react'
import { useRouteMatch } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import { setTask } from '../reducers/userTaskReducer'
import Form from './Form'

const Task = () => {
    const dispatch = useDispatch()
    const task = useSelector(state => state.userTasks.active)

    const idMatch = useRouteMatch('/dashboard/tasks/:id')
    idMatch && dispatch(setTask(idMatch.params.id))

    switch (task.type) {
    case 'FORM': {
        return (
            <Form form={task.formFields} onSubmit={}/>
        )
    }
    case 'PAYMENT': {
        return (
            <p>PAYMENT</p>
        )
    }
    default: {
        return null
    }
    }
}

export default Task