<script>
    import {onMount} from "svelte";
    import CoderList from "./components/CoderList.svelte";
    import {coders} from "./store/store.js";
    import NewCoderDialog from "./components/NewCoderDialog.svelte";

    onMount(async () => {
        //const response = await fetch('http://localhost:9000/api/coders')
        //coders = await response.json();
        //console.log(coders)
        fetch('http://localhost:9000/api/coders')
            .then(response => response.json())
            .then(data => coders.set(data))
    })

    let showCreateDialog = false;

    const toggleShowCreateDialog = () => {
        showCreateDialog = !showCreateDialog
    }

</script>

<main>
    <nav class="top-bar">
        <div class="top-bar-div">Coder App</div>
        <button on:click={toggleShowCreateDialog}>Add</button>
    </nav>
    {#if showCreateDialog}
        <NewCoderDialog/>
    {/if}


    <CoderList/>

</main>

<style>
    .top-bar {
        background-color: blue;
        height: 80px;
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .top-bar-div {
        color: white;
    }

    @media (min-width: 640px) {
        main {
            max-width: none;
        }
    }
</style>