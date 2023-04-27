<script>


import {coders} from "../store/store.js";
</script>

<section>

    {#each $coders as coder}
        <div>{coder.name} + {coder.favoriteLanguage} + {coder.imageUrl}</div>
    {/each}



</section>

<style>

</style>