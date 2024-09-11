<script>
    let poll = {
        pollId: "",
        userId: "",
        question: "",
        publishedAt: new Date().toISOString(),
        validUntil: '',
        voteOption: []
    }

    let newOption = "";

    function addVoteOption() {
        poll.voteOption = [...poll.voteOption, {voteOptionId: '', caption: '', presentationOrder: poll.voteOption.length}]; //+1
    }

    const createPoll = async () => {
        try {
            const response = await fetch('http://localhost:8080/polls', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(poll)
            });
            if (response.ok) {
                console.log('Poll created: ', await response.json());
            }
            else {
                console.error('Failed to create poll');
            }
        }
        catch (error) {
            console.error('Error: ', error);
        }
    }

</script>

<form>
    <h2>Create Poll</h2>
    <input bind:value={poll.username} placeholder="Username" />
    <input bind:value={poll.question} placeholder="Question" />

    {#each poll.voteOption as option, i}
        <input bind:value={poll.voteOption[i].caption} placeholder='Vote Option Caption' />
    {/each}

    <button on:click={addVoteOption}>Add Vote Option</button>
    <button on:click={createPoll}>Create Poll</button>
</form>