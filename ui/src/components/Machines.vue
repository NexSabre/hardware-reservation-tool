<template>
  <div>
        Count machines: {{ allMachines }} <br>
       {{ machines }}
  </div>
</template>

<script>
export default {
    name: "Machines",
    data() {
        return {
            machines: []
        }
    },
    methods: {
        getMachines() {
            const baseURI = "http://localhost:8080/api/v1/machines"
            this.$http.get(baseURI)
                .then(result => this.machines = result.data)
        }
    }, 
    mounted() {
        this.getMachines()
    }
}
</script>

<style>

</style>