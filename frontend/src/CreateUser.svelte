<script>
    let user = {
        username: '',
        email: ''
    };

    const createUser = async () => {
        try {
            const response = await fetch('http://localhost:8080/users', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            });
            if (response.ok) {
                console.log('User created:', await response.json());
                // Nullstill feltene etter at brukeren er opprettet
                user.username = '';
                user.email = '';
            } else {
                console.error('Failed to create user');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    }
</script>

<form>
    <h2>Create User</h2>
    <input bind:value={user.username} placeholder="Username" required/>
    <input bind:value={user.email} placeholder="Email" type="email" required/>
    <button on:click={createUser}>Create User</button>
</form>