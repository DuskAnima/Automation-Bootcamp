document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('registroForm');
    const successMsg = document.getElementById('successMsg');

    form.addEventListener('submit', function (event) {
        event.preventDefault();
        event.stopPropagation();

        let valid = true;

        // Nombre
        const nombre = form.nombre;
        if (!nombre.value.trim()) {
            nombre.classList.add('is-invalid');
            valid = false;
        } else {
            nombre.classList.remove('is-invalid');
        }

        // Email
        const email = form.email;
        const emailRegex = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
        if (!emailRegex.test(email.value)) {
            email.classList.add('is-invalid');
            valid = false;
        } else {
            email.classList.remove('is-invalid');
        }

        // Password
        const password = form.password;
        if (password.value.length < 6) {
            password.classList.add('is-invalid');
            valid = false;
        } else {
            password.classList.remove('is-invalid');
        }

        if (valid) {
            successMsg.classList.remove('d-none');
            form.reset();
            setTimeout(() => {
                successMsg.classList.add('d-none');
            }, 3000);
        }
    });

    // Remover feedback al escribir
    ['nombre', 'email', 'password'].forEach(id => {
        form[id].addEventListener('input', function () {
            this.classList.remove('is-invalid');
        });
    });
});
