import matplotlib.pyplot as plt
import pandas as pd

def plot_eda(df, col):
    plt.scatter(df[df['out/in'] == 'Out']['temp'], df[df['out/in'] == 'Out']['temp'], label='out')
    plt.scatter(df[df['out/in'] == 'In']['temp'], df[df['out/in'] == 'In']['temp'], label='in')
    plt.legend()
    plt.savefig(f'./rule_generation/eda.png')
    plt.show()


df = pd.read_csv('./rule_generation/IOT-temp.csv')
df = df.drop(columns=['id','room_id/id','noted_date'])

print('Count of each class:')
print(df.value_counts())

print(df['out/in'].value_counts())

plot_eda(df, 'out/in')
