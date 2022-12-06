import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

# Generate gaussian distribution with mean 0 and std 1
np.random.seed(42)

def inside_room(x):
    return 'inside' if x <= 30 else 'outside'

def outlier_label(x):
    return 'outside' if x <= 30 else 'inside'


# Generate 1000 samples
xs = np.random.normal(30, 6, int(1e5))
result = np.vectorize(inside_room)(xs)

# Add noise
# xs += np.random.normal(0, 1, 100000)

# Add outliers
xs_outliers = np.random.normal(30, 6, int(2e3))
result_outliers = np.vectorize(outlier_label)(xs_outliers)

xs = np.append(xs, xs_outliers)
result = np.append(result, result_outliers)


df = pd.DataFrame({'temp': xs, 'result': result})

# shuffle dataframe
df = df.sample(frac=1).reset_index(drop=True)

df.to_csv('./rule_generation_synthetic/IOT-temp-synthetic.csv', index=False)

# print(df[:10])
# # print(df['result'][:10])
# plt.hist(df['temp'][df['result']=='inside'])
# plt.show()

# plt.hist(df['temp'][df['result']=='outside'])
# plt.show()
