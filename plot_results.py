import pandas as pd
import matplotlib.pyplot as plt
import os

df = pd.read_csv("results/results.csv")

os.makedirs("docs/plots", exist_ok=True)

# ---------- График 1: Time vs n ----------
plt.figure(figsize=(10, 6))

for algorithm in df["algorithm"].unique():
    subset = df[df["algorithm"] == algorithm]
    avg_by_n = subset.groupby("n")["time_ns"].mean().reset_index()
    plt.plot(avg_by_n["n"], avg_by_n["time_ns"], marker="o", label=algorithm)

plt.xlabel("Input size (n)")
plt.ylabel("Time (ns)")
plt.title("Execution Time vs Input Size")
plt.xscale("log")
plt.yscale("log")
plt.legend()
plt.grid(True, which="both", linestyle="--", alpha=0.5)
plt.savefig("docs/plots/time_vs_n.png", dpi=150, bbox_inches="tight")
plt.close()

# ---------- График 2: Recursion depth vs n ----------
plt.figure(figsize=(10, 6))

for algorithm in ["MergeSort", "QuickSort"]:  # только эти считают глубину
    subset = df[df["algorithm"] == algorithm]
    avg_depth = subset.groupby("n")["max_depth"].mean().reset_index()
    plt.plot(avg_depth["n"], avg_depth["max_depth"], marker="o", label=algorithm)

plt.xlabel("Input size (n)")
plt.ylabel("Max recursion depth")
plt.title("Recursion Depth vs Input Size")
plt.xscale("log")
plt.legend()
plt.grid(True, which="both", linestyle="--", alpha=0.5)
plt.savefig("docs/plots/depth_vs_n.png", dpi=150, bbox_inches="tight")
plt.close()

print("Графики сохранены в docs/plots/")