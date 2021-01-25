<template>
  <div class="card" style="margin-bottom: 10px">
    <div class="card-body">
      <h5 class="card-title">#{{ machine.id }} {{ machine.name }}</h5>
      <h6 class="card-subtitle mb-2 text-muted">{{ machine.address }}</h6>
      <p class="card-text">
        <!-- Start: {{ machine.start }}<br /> -->
        <!-- Ends: {{ machines.ends }} -->
      </p>
      <span class="badge badge-primary">Enabled: {{ machine.enabled }}</span>
      <span class="badge badge-warning">Protected: {{ machine.protected }}</span>
    </div>
  </div>
</template>

<script>
export default {
    name: "MachineCards",
    props: {
        machine: {required: true, type: Object}
    }
};
</script>

<style>
</style>