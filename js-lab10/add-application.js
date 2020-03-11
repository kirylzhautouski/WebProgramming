function createLabel(forInput, labelClass, text) {
    const label = document.createElement('label');
    label.setAttribute('for', forInput);
    label.setAttribute('class', labelClass);
    label.textContent = text;
    return label;
}

function createInput(type, id, inputClass, validator) {
    const input = document.createElement('input');
    input.setAttribute('type', type);
    input.setAttribute('id', id);
    input.setAttribute('class', inputClass);
    input.addEventListener('input', function(evt) {
        validator(evt.target);
    });
    return input;
}

function validateName(inputElement) {
    const value = inputElement.value;

    if (value.length == 0 || value.length > 20) {
        inputElement.nextSibling.style.display = 'block';
        inputElement.nextSibling.textContent = '*поле должно быть длины от 0 до 20 не включая';
        
        return false;
    } else {
        inputElement.nextSibling.style.display = 'none';

        return true;
    }
}

function validateFaculty(inputElement) {
    const value = inputElement.value;

    if (value.length == 0) {
        inputElement.nextSibling.style.display = 'block';
        inputElement.nextSibling.textContent = '*поле должно быть не пусто';

        return false;
    } else {
        inputElement.nextSibling.style.display = 'none';

        return true;
    }
}

function validateGrade(inputElement) {
    const value = inputElement.value;

    if (value.length == 0) {
        inputElement.nextSibling.style.display = 'block';
        inputElement.nextSibling.textContent = '*поле должно быть не пусто';

        return false;
    }

    const numberValue = Number(value);
    if (isNaN(numberValue)) {
        inputElement.nextSibling.style.display = 'block';
        inputElement.nextSibling.textContent = '*поле должно быть десятичным числом';

        return false;
    } else {
        inputElement.nextSibling.style.display = 'none';

        return true;
    }

}

function populateForm(formId, elements) {
    const form = document.getElementById(formId);

    elements.inputs.forEach(inputInfo => {
        form.appendChild(createLabel(inputInfo.inputId, inputInfo.labelClass, inputInfo.labelText));
        form.appendChild(createInput(inputInfo.inputType, inputInfo.inputId, inputInfo.inputClass, inputInfo.validator));
        form.appendChild(createLabel(inputInfo.inputId, inputInfo.labelClass + ' label-error', ''));
    });

    const button = document.createElement('button');
    button.setAttribute('class', elements.button.class);
    button.textContent = elements.button.text;
    button.onclick = function() {
        validateName(document.getElementById('first-name'));
        validateName(document.getElementById('last-name'));
        validateFaculty(document.getElementById('faculty'));
        validateGrade(document.getElementById('average-grade'));
    };

    form.appendChild(button);
}

const formElements = {
    inputs: [
        {
            labelText: 'Имя',
            labelClass: 'label',
            inputId: 'first-name',
            inputType: 'text',
            inputClass: 'input',
            validator: validateName,
        },
        {
            labelText: 'Фамилия',
            labelClass: 'label',
            inputId: 'last-name',
            inputType: 'text',
            inputClass: 'input',
            validator: validateName,
        },
        {
            labelText: 'Факультет',
            labelClass: 'label',
            inputId: 'faculty',
            inputType: 'text',
            inputClass: 'input',
            validator: validateFaculty,
        },
        {
            labelText: 'Средний балл',
            labelClass: 'label',
            inputId: 'average-grade',
            inputType: 'text',
            inputClass: 'input',
            validator: validateGrade,
        },
    ],

    button: {
        text: 'Добавить заявку',
        class: 'button',
    },
};

populateForm('add-application-form', formElements);