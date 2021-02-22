import React from 'react'
import { ButtonGroup, ToggleButton } from 'react-bootstrap'

const BoolOperationButton = ({ name, value, setValue }) => {
    const operations = [
        { name: '&', value: 'AND' },
        { name: '|', value: 'OR' }
    ]

    return (
        <ButtonGroup toggle>
            {
                operations.map(operation => (
                    <ToggleButton
                        size="sm"
                        key={operation.name}
                        value={operation.value}
                        type="radio"
                        variant="secondary"
                        name="radio"
                        checked={value === operation.value}
                        onChange={({ currentTarget }) => setValue(name, currentTarget)}
                        style={{ width:'20%', textAlign:'center' }}
                    >
                        {operation.name}
                    </ToggleButton>
                ))
            }
        </ButtonGroup>
    )
}

export default BoolOperationButton