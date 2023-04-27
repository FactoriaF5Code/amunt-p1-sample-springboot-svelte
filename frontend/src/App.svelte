<script>
	export let name;
</script>

<main>
	<nav class="top-bar">
		<div class="top-bar-div">Coder App</div>
	</nav>

</main>

<style>
	.top-bar {
		background-color: blue;
		height: 80px;
		width: 100%;
		display:flex;
		justify-content: center;
		align-items: center;
	}

	.top-bar-div{
		color:white;
	}

	@media (min-width: 640px) {
		main {
			max-width: none;
		}
	}
</style>