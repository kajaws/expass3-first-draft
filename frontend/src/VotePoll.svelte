<script>
    let vote = {
        pollId: "",
        voteId: "",
        userId: "",
        voteOptionId: "",
        poll: {
            question: "",
            voteOption: []
        },
        selectedOption: null
    };

    // List to hold the polls fetched from the backend
    let polls = [];

    // Fetch all polls from the backend
    async function loadAllPolls() {
        try {
            const response = await fetch('http://localhost:8080/polls');
            if (response.ok) {
                polls = await response.json();
            }
            else {
                alert('Failed to load polls');
            }
        }
        catch (error) {
            console.error('Error fetching polls: ', error);
        }
    }

    async function loadPoll() {
        if (!vote.pollId) {
            alert('Please enter a poll ID');
        }
        try {
            const response = await fetch(`http://localhost:8080/polls/${vote.pollId}`);
            if (response.ok) {
                vote.poll = await response.json();
            }
            else {
                alert('Failed to load poll');
            }
        }
        catch (error) {
            console.error('Error loading poll: ', error);
        }
    }

    const createVote = async () => {
        vote.voteOptionId = vote.selectedOption;
        try {
            const response = await fetch('http://localhost:8080/votes', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(vote)
            });
            if (response.ok) {
                console.log('Vote created: ', await response.json());
            }
            else {
                console.error('Failed to create vote');
            }
        }
        catch (error) {
            console.error('Error creating vote: ', error);
        }
    }

    // Load all polls when the component is mounted
    loadAllPolls();
</script>

<form>
    <h2>Vote</h2>
    <input bind:value={vote.username} placeholder="Username" />

    <!-- Dropdown for selecting poll question -->
    <select bind:value={vote.pollId} on:change={loadPoll}>
        <option disabled value="">Select Poll</option>
        {#each polls as poll}
            <option value={poll.pollId}>{poll.question}</option>
        {/each}
    </select>

    <!-- Load poll button -->
    <button on:click={loadPoll}>Load Poll</button>

    <!-- Idf a poll is loaded, display its question and options -->
    {#if vote.poll.question}
        <h3>{vote.poll.question}</h3>
        {#each vote.poll.voteOption as option}
            <label>
                <input type="radio" name="voteOption" bind:group={vote.selectedOption} value={option.voteOptionId} />
                {option.caption}
            </label>
        {/each}
        <button on:click={createVote}> Submit Vote</button>
    {/if}
</form>